package com.example.siyai_front_android

import android.app.Application
import com.example.siyai_front_android.di.AppComponent
import com.example.siyai_front_android.di.DaggerAppComponent

class SiyAiApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
