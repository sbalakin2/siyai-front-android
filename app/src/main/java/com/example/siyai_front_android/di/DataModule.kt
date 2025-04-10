package com.example.siyai_front_android.di

import com.example.siyai_front_android.data.repositories.LetsMeetRepositoryImpl
import com.example.siyai_front_android.data.repositories.LoginRepositoryImpl
import com.example.siyai_front_android.data.repositories.RecoveryPasswordRepositoryImpl
import com.example.siyai_front_android.data.repositories.RegRepositoryImpl
import com.example.siyai_front_android.domain.repositories.LetsMeetRepository
import com.example.siyai_front_android.domain.repositories.LoginRepository
import com.example.siyai_front_android.domain.repositories.RecoveryPasswordRepository
import com.example.siyai_front_android.domain.repositories.RegRepository
import dagger.Binds
import dagger.Module

@Module(includes = [RetrofitModule::class])
interface DataModule {

    @Binds
    fun bindLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindRegRepository(regRepository: RegRepositoryImpl): RegRepository

    @Binds
    fun bindRecoveryPasswordRepository(
        recoveryPasswordRepository: RecoveryPasswordRepositoryImpl
    ): RecoveryPasswordRepository

    @Binds
    fun bindLetsMeetRepository(letsMeetRepository: LetsMeetRepositoryImpl): LetsMeetRepository
}