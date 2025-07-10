package com.example.siyai_front_android

import android.app.Application
import com.example.siyai_front_android.di.AppComponent

class SiyAiApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this)
    }
}
