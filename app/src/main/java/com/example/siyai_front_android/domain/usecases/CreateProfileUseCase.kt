package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.UserProfileData
import com.example.siyai_front_android.presentation.auth.welcome.LetsMeetState

interface CreateProfileUseCase {
    suspend operator fun invoke(data: UserProfileData): LetsMeetState
}