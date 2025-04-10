package com.example.siyai_front_android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siyai_front_android.presentation.login.LoginViewModel
import com.example.siyai_front_android.presentation.password_recovery.RecoveryPasswordViewModel
import com.example.siyai_front_android.presentation.reg.RegViewModel
import com.example.siyai_front_android.presentation.welcome.LetsMeetViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [UseCaseModule::class])
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

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
}
