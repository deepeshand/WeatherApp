package com.example.weatherapp.di

import com.example.weatherapp.models.WeatherModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("weather?&appid=a567eca4b52f20847650b5f73af13af9")
    suspend fun getWeatherResponse(@Query(value = "q") query: String) :Response<WeatherModel>
}