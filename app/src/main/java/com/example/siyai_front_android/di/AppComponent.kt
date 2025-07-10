package com.example.siyai_front_android.di

import android.content.Context
import com.example.siyai_front_android.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    companion object {
        fun create(context: Context): AppComponent {
            return DaggerAppComponent
                .factory()
                .create(context)
        }
    }
}
