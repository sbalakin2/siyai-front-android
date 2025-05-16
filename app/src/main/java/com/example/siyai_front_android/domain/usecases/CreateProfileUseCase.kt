package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.CreateProfileData
import com.example.siyai_front_android.presentation.auth.lets_meet.LetsMeetState

interface CreateProfileUseCase {
    suspend operator fun invoke(data: CreateProfileData): LetsMeetState
}