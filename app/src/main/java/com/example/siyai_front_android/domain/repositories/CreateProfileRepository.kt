package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.CreateProfileData
import com.example.siyai_front_android.utils.NetworkResult

interface CreateProfileRepository {
    suspend fun createUserProfile(data: CreateProfileData): NetworkResult<Unit>
}