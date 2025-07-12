package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.Profile
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ProfileStorageRepository {

    suspend fun profileLastUpdateTime(): Date?

    suspend fun getUserProfileFlow(): Flow<Profile?>

    suspend fun getEmail(): String

    suspend fun saveUserProfile(profile: Profile)

    suspend fun clear()
}