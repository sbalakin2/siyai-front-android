package com.example.siyai_front_android.di

import android.content.Context
import com.example.siyai_front_android.data.local.AppDatabase
import com.example.siyai_front_android.data.local.DailyDao
import com.example.siyai_front_android.data.local.MyStateDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideMyStateDao(database: AppDatabase): MyStateDao = database.myStateDao()

    @Singleton
    @Provides
    fun provideDailyDao(database: AppDatabase): DailyDao = database.dailyDao()
}
