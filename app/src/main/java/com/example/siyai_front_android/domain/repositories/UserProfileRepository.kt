package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.UserProfileData

interface UserProfileRepository {

    suspend fun getUserProfile(): Result<UserProfileData>

    suspend fun saveUserProfile(profile: UserProfileData)
}