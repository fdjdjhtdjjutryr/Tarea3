package com.example.aaaaaaaa

import android.app.Application
import com.example.aaaaaaaa.Data.AppContainer
import com.example.aaaaaaaa.Data.AppDataContainer


class Unidad3Application : Application(){


    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}