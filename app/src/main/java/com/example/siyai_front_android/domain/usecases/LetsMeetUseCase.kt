package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.model.UserProfileData
import com.example.siyai_front_android.presentation.auth.welcome.LetsMeetState

interface LetsMeetUseCase {
    suspend operator fun invoke(data: UserProfileData): LetsMeetState
}