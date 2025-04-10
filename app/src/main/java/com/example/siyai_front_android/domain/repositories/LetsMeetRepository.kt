package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.model.UserProfileData
import com.example.siyai_front_android.utils.NetworkResult

interface LetsMeetRepository {
    suspend fun createUserProfile(data: UserProfileData): NetworkResult<Unit>
}