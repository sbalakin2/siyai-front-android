package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.utils.NetworkResult

interface DeleteProfileRepository {
    suspend fun deleteProfile(email: String): NetworkResult<String>
}
