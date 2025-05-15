package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.CacheContainer
import com.example.siyai_front_android.domain.dto.Profile

interface ProfileStorageRepository {

    suspend fun getUserEmail(): Result<String>

    suspend fun getUserProfile(): Result<CacheContainer<Profile>>

    suspend fun saveUserEmail(email: String)

    suspend fun saveUserProfile(profile: Profile)

    suspend fun clear()
}