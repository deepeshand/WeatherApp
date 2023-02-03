package com.example.weatherapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.di.RetroServiceInterface
import com.example.weatherapp.models.WeatherModel
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    var weatherresposne : MutableLiveData<Response<WeatherModel>> = MutableLiveData()


    @Inject
    lateinit var retrofitServiceInterface: RetroServiceInterface

    init {
        (application as MyApplication).getRetroFitObject().inject(this)
    }

    fun fetchweatherdata(location : String): MutableLiveData<Response<WeatherModel>> {
        CoroutineScope(Dispatchers.IO).launch {
            var response = retrofitServiceInterface.getWeatherResponse(location)
            weatherresposne.postValue(response)
        }
        return weatherresposne
    }
}
