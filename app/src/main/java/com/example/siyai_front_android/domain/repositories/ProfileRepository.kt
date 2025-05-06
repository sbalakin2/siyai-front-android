package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.utils.NetworkResult

interface ProfileRepository {
    suspend fun getProfile(email: String): NetworkResult<Profile>
}