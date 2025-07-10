package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.auth.password_recovery.RecoveryPasswordState

interface RecoveryPasswordUseCase {
    suspend operator fun invoke(email: String): RecoveryPasswordState
}
