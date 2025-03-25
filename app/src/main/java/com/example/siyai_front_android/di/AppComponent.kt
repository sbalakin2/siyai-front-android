package com.example.siyai_front_android.di

import com.example.siyai_front_android.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
