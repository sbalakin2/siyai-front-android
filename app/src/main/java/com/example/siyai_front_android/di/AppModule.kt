package com.example.siyai_front_android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
 class AppModule {

    @Singleton
    @Provides
    fun provideViewModelMap(): Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>> {
        return emptyMap()
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(
        creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(creators)
    }
}
