package com.example.siyai_front_android.di

import com.example.siyai_front_android.domain.usecases.CreateProfileUseCase
import com.example.siyai_front_android.domain.usecases.CreateProfileUseCaseImpl
import com.example.siyai_front_android.domain.usecases.EditProfileUseCase
import com.example.siyai_front_android.domain.usecases.EditProfileUseCaseImpl
import com.example.siyai_front_android.domain.usecases.GetCountiesWithCitiesUseCase
import com.example.siyai_front_android.domain.usecases.GetCountiesWithCitiesUseCaseImpl
import com.example.siyai_front_android.domain.usecases.GetProfileUseCase
import com.example.siyai_front_android.domain.usecases.GetProfileUseCaseImpl
import com.example.siyai_front_android.domain.usecases.LoginUseCase
import com.example.siyai_front_android.domain.usecases.LoginUseCaseImpl
import com.example.siyai_front_android.domain.usecases.RecoveryPasswordUseCase
import com.example.siyai_front_android.domain.usecases.RecoveryPasswordUseCaseImpl
import com.example.siyai_front_android.domain.usecases.RegUseCase
import com.example.siyai_front_android.domain.usecases.RegUseCaseImpl
import com.example.siyai_front_android.domain.usecases.VerifyUseCase
import com.example.siyai_front_android.domain.usecases.VerifyUseCaseImpl
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
    fun bindCreateProfileUseCase(createProfileUseCase: CreateProfileUseCaseImpl): CreateProfileUseCase

    @Binds
    fun bindVerifyUseCase(verifyUseCase: VerifyUseCaseImpl): VerifyUseCase

    @Binds
    fun bindCountryWithCitiesUseCase(
        countryWithCitiesUseCase: GetCountiesWithCitiesUseCaseImpl
    ): GetCountiesWithCitiesUseCase

    @Binds
    fun bindEditProfileUseCase(editProfileUseCase: EditProfileUseCaseImpl): EditProfileUseCase

    @Binds
    fun bindGetProfileUseCase(getProfileUseCaseImpl: GetProfileUseCaseImpl): GetProfileUseCase
}