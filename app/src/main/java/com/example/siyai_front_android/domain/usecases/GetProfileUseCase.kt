package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.profile.ProfileState

interface GetProfileUseCase {
    suspend operator fun invoke(): ProfileState
}