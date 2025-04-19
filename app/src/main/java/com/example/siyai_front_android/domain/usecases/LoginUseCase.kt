package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.auth.login.LoginState

interface LoginUseCase {
    suspend operator fun invoke(email: String, password: String): LoginState
}