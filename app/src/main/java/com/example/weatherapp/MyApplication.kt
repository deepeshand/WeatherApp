package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.DaggerRetroComponent
import com.example.weatherapp.di.RetroComponent
import com.example.weatherapp.di.RetroModule

class MyApplication : Application() {

    lateinit var retroComponent : RetroComponent
    override fun onCreate() {
        super.onCreate()
        retroComponent = DaggerRetroComponent.builder().retroModule(RetroModule()).build()
    }

    fun getRetroFitObject() : RetroComponent{
        return retroComponent
    }
}