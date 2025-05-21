package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.AuthProgress

interface EnterToAppUseCase {
    suspend operator fun invoke(progress: AuthProgress)
}