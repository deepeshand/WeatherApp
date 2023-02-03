package com.example.weatherapp.di

import com.example.weatherapp.WeatherViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {
    fun inject(weatherViewModel: WeatherViewModel)
}