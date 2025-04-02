package com.example.siyai_front_android.di

import com.example.siyai_front_android.data.repositories.RegRepositoryImpl
import com.example.siyai_front_android.domain.repositories.RegRepository
import dagger.Binds
import dagger.Module

@Module(includes = [RetrofitModule::class])
interface DataModule {
    @Binds
    fun bindRegRepository(regRepository: RegRepositoryImpl): RegRepository
}
