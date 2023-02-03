package com.example.weatherapp.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetroModule {

    val baseUrl = "https://api.openweathermap.org/data/2.5/"

    @Singleton
    @Provides
    fun getRetroServiceInterface(retrofit: Retrofit) : RetroServiceInterface{
          return  retrofit.create(RetroServiceInterface::class.java)
    }

    @Singleton
    @Provides
    fun getRetroFitInstance(): Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).
        addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    }

}