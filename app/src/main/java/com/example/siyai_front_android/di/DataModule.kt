package com.example.siyai_front_android.di

import com.example.siyai_front_android.data.repositories.AuthStatusRepositoryImpl
import com.example.siyai_front_android.data.repositories.CountryWithCitiesRepositoryImpl
import com.example.siyai_front_android.data.repositories.CreateProfileRepositoryImpl
import com.example.siyai_front_android.data.repositories.LoginRepositoryImpl
import com.example.siyai_front_android.data.repositories.ProfileEditingRepositoryImpl
import com.example.siyai_front_android.data.repositories.ProfileRepositoryImpl
import com.example.siyai_front_android.data.repositories.RecoveryPasswordRepositoryImpl
import com.example.siyai_front_android.data.repositories.RegRepositoryImpl
import com.example.siyai_front_android.data.repositories.UserProfileRepositoryImpl
import com.example.siyai_front_android.data.repositories.VerificationRepositoryImpl
import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import com.example.siyai_front_android.domain.repositories.CountryWithCitiesRepository
import com.example.siyai_front_android.domain.repositories.CreateProfileRepository
import com.example.siyai_front_android.domain.repositories.LoginRepository
import com.example.siyai_front_android.domain.repositories.ProfileEditingRepository
import com.example.siyai_front_android.domain.repositories.ProfileRepository
import com.example.siyai_front_android.domain.repositories.RecoveryPasswordRepository
import com.example.siyai_front_android.domain.repositories.RegRepository
import com.example.siyai_front_android.domain.repositories.UserProfileRepository
import com.example.siyai_front_android.domain.repositories.VerificationRepository
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
    fun bindCreateProfileRepository(
        createProfileRepository: CreateProfileRepositoryImpl
    ): CreateProfileRepository

    @Binds
    fun bindCountryWithCitiesRepository(
        countryWithCitiesRepository: CountryWithCitiesRepositoryImpl
    ): CountryWithCitiesRepository

    @Binds
    fun bindVerificationRepository(
        verificationRepository: VerificationRepositoryImpl
    ): VerificationRepository


    @Binds
    fun bindAuthStatusRepository(
        authStatusRepository: AuthStatusRepositoryImpl
    ): AuthStatusRepository


    @Binds
    fun bindUserProfileRepository(
        userProfileRepository: UserProfileRepositoryImpl
    ): UserProfileRepository

    @Binds
    fun bindProfileEditingRepository(
        profileEditingRepositoryImpl: ProfileEditingRepositoryImpl
    ): ProfileEditingRepository

    @Binds
    fun bindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository
}