package com.example.siyai_front_android.di

import com.example.siyai_front_android.domain.usecases.CreateProfileUseCase
import com.example.siyai_front_android.domain.usecases.CreateProfileUseCaseImpl
import com.example.siyai_front_android.domain.usecases.DeleteProfileUseCase
import com.example.siyai_front_android.domain.usecases.DeleteProfileUseCaseImpl
import com.example.siyai_front_android.domain.usecases.EditProfileUseCase
import com.example.siyai_front_android.domain.usecases.EditProfileUseCaseImpl
import com.example.siyai_front_android.domain.usecases.EnterToAppUseCase
import com.example.siyai_front_android.domain.usecases.EnterToAppUseCaseImpl
import com.example.siyai_front_android.domain.usecases.ExitFromAppUseCase
import com.example.siyai_front_android.domain.usecases.ExitFromAppUseCaseImpl
import com.example.siyai_front_android.domain.usecases.GetAuthStatusUseCase
import com.example.siyai_front_android.domain.usecases.GetAuthStatusUseCaseImpl
import com.example.siyai_front_android.domain.usecases.GetCountiesWithCitiesUseCase
import com.example.siyai_front_android.domain.usecases.GetCountiesWithCitiesUseCaseImpl
import com.example.siyai_front_android.domain.usecases.GetProfileUseCase
import com.example.siyai_front_android.domain.usecases.GetProfileUseCaseImpl
import com.example.siyai_front_android.domain.usecases.LoginUseCase
import com.example.siyai_front_android.domain.usecases.LoginUseCaseImpl
import com.example.siyai_front_android.domain.usecases.MyStateAddCycleUseCase
import com.example.siyai_front_android.domain.usecases.MyStateAddCycleUseCaseImpl
import com.example.siyai_front_android.domain.usecases.MyStateDeleteCycleUseCase
import com.example.siyai_front_android.domain.usecases.MyStateDeleteCycleUseCaseImpl
import com.example.siyai_front_android.domain.usecases.MyStateGetCyclesUseCase
import com.example.siyai_front_android.domain.usecases.MyStateGetCyclesUseCaseImpl
import com.example.siyai_front_android.domain.usecases.RecoveryPasswordUseCase
import com.example.siyai_front_android.domain.usecases.RecoveryPasswordUseCaseImpl
import com.example.siyai_front_android.domain.usecases.RegUseCase
import com.example.siyai_front_android.domain.usecases.RegUseCaseImpl
import com.example.siyai_front_android.domain.usecases.ResetPasswordUseCase
import com.example.siyai_front_android.domain.usecases.ResetPasswordUseCaseImpl
import com.example.siyai_front_android.domain.usecases.VerifyUseCase
import com.example.siyai_front_android.domain.usecases.VerifyUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

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
    fun bindGetAuthStatusUseCase(
        getAuthStatusUseCase: GetAuthStatusUseCaseImpl
    ): GetAuthStatusUseCase

    @Binds
    fun bindEnterToAppUseCase(enterToAppUseCase: EnterToAppUseCaseImpl): EnterToAppUseCase

    @Binds
    fun bindExitFromAppUseCase(exitFromAppUseCase: ExitFromAppUseCaseImpl): ExitFromAppUseCase

    @Binds
    fun bindEditProfileUseCase(editProfileUseCase: EditProfileUseCaseImpl): EditProfileUseCase

    @Binds
    fun bindGetProfileUseCase(getProfileUseCaseImpl: GetProfileUseCaseImpl): GetProfileUseCase

    @Binds
    fun bindResetPasswordUseCase(
        resetPasswordUseCaseImpl: ResetPasswordUseCaseImpl
    ): ResetPasswordUseCase

    @Binds
    fun bindDeleteProfileUseCase(
        deleteProfileUseCaseImpl: DeleteProfileUseCaseImpl
    ): DeleteProfileUseCase

    @Binds
    fun bindMyStateAddCycleUseCase(
        myStateAddCycleUseCaseImpl: MyStateAddCycleUseCaseImpl
    ): MyStateAddCycleUseCase

    @Binds
    fun bindMyStateDeleteCycleUseCase(
        myStateDeleteCycleUseCaseImpl: MyStateDeleteCycleUseCaseImpl
    ): MyStateDeleteCycleUseCase

    @Binds
    fun bindMyStateGetCyclesUseCase(
        myStateGetCyclesUseCaseImpl: MyStateGetCyclesUseCaseImpl
    ): MyStateGetCyclesUseCase
}