package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.auth.reg.RegState

interface RegUseCase {
    suspend operator fun invoke(email: String, password: String): RegState
}
