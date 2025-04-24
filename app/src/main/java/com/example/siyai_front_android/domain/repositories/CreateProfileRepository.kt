package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.UserProfileData
import com.example.siyai_front_android.utils.NetworkResult

interface CreateProfileRepository {
    suspend fun createUserProfile(data: UserProfileData): NetworkResult<Unit>
}