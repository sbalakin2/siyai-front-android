package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Profile

interface GetUserProfileUseCase {
    suspend operator fun invoke(): Result<Profile>
}