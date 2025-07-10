package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.profile.ProfileState
import kotlinx.coroutines.flow.Flow

interface GetProfileUseCase {
    suspend operator fun invoke(): Flow<ProfileState>
}