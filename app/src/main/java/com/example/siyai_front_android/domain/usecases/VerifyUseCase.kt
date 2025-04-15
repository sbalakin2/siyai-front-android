package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.email_confirmation.VerificationState

interface VerifyUseCase {
    suspend operator fun invoke(email: String): VerificationState
}
