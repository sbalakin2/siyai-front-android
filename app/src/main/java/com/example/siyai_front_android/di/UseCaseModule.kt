package com.example.siyai_front_android.di

import com.example.siyai_front_android.domain.usecases.LetsMeetUseCase
import com.example.siyai_front_android.domain.usecases.LetsMeetUseCaseImpl
import com.example.siyai_front_android.domain.usecases.LoginUseCase
import com.example.siyai_front_android.domain.usecases.LoginUseCaseImpl
import com.example.siyai_front_android.domain.usecases.RecoveryPasswordUseCase
import com.example.siyai_front_android.domain.usecases.RecoveryPasswordUseCaseImpl
import com.example.siyai_front_android.domain.usecases.RegUseCase
import com.example.siyai_front_android.domain.usecases.RegUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DataModule::class])
interface UseCaseModule {

    @Binds
    fun bindRegUseCase(regUseCase: RegUseCaseImpl): RegUseCase

    @Binds
    fun bindLoginUseCase(loginUseCase: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindRecoveryPasswordUseCase(
        recoveryPasswordUseCase: RecoveryPasswordUseCaseImpl
    ): RecoveryPasswordUseCase

    @Binds
    fun bindLetsMeetUseCase(letsMeetUseCase: LetsMeetUseCaseImpl): LetsMeetUseCase
}