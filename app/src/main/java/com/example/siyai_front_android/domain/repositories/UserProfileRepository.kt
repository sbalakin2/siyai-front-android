package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.CreateProfileData
import com.example.siyai_front_android.domain.dto.Profile

interface UserProfileRepository {

    suspend fun getUserProfile(): Result<Profile>

    suspend fun saveUserProfile(profile: CreateProfileData)
}