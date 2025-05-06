package com.example.siyai_front_android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siyai_front_android.presentation.auth.email_confirmation.EmailConfirmationViewModel
import com.example.siyai_front_android.presentation.auth.lets_meet.LetsMeetViewModel
import com.example.siyai_front_android.presentation.auth.login.LoginViewModel
import com.example.siyai_front_android.presentation.auth.password_recovery.RecoveryPasswordViewModel
import com.example.siyai_front_android.presentation.auth.reg.RegViewModel
import com.example.siyai_front_android.presentation.profile_editing.ProfileEditingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [UseCaseModule::class])
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(EmailConfirmationViewModel::class)
    abstract fun bindEmailConfigurationViewModel(
        emailConfirmationViewModel: EmailConfirmationViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegViewModel::class)
    abstract fun bindRegViewModel(regViewModel: RegViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecoveryPasswordViewModel::class)
    abstract fun bindRecoveryPasswordViewModel(recoveryPasswordViewModel: RecoveryPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LetsMeetViewModel::class)
    abstract fun bindLetsMeetViewModel(letsMeetViewModel: LetsMeetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileEditingViewModel::class)
    abstract fun bindProfileEditingViewModel(
        profileEditingViewModel: ProfileEditingViewModel
    ): ViewModel
}
