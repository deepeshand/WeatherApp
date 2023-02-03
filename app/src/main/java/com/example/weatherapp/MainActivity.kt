package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.models.WeatherModel
import retrofit2.Response
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        setContentView(binding.root)
        var response = weatherViewModel.fetchweatherdata("ottawa")
        response.observe(this, Observer {
            setData(it)
        })
    }

    private fun setData(it: Response<WeatherModel>?) {


        //binding.name
        binding.name.setText(it?.body()?.name)

        var temp = (((it?.body()?.main?.temp?.minus(273))?.times(100.0))?.roundToInt()
            ?.div(100.0)).toString()
        binding.temp.setText(temp)
        binding.description.setText(it?.body()?.weather?.get(0)?.description?.toString())
        binding.degree.setText(it?.body()?.wind?.deg?.toString())
        binding.humidity.setText(it?.body()?.main?.humidity?.toString())
        binding?.speed?.setText(it?.body()?.wind?.speed?.toString())

        when (it?.body()?.weather?.get(0)?.description) {
            "clear sky", "mist" -> {
                Glide.with(this@MainActivity)
                    .load(R.drawable.clouds)
                    .into(binding.image)
            }
            "haze", "overcast clouds" -> {
                Glide.with(this@MainActivity)
                    .load(R.drawable.haze)
                    .into(binding.image)
            }
            else -> {
                Glide.with(this@MainActivity)
                    .load(R.drawable.rain)
                    .into(binding.image)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}