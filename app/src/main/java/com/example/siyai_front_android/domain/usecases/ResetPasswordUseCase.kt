package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.auth.password_reset.PasswordResetState

interface ResetPasswordUseCase {
    suspend operator fun invoke(token: String, newPassword: String): PasswordResetState
}