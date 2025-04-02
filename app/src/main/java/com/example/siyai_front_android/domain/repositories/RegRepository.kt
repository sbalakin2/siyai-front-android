package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.utils.NetworkResult

interface RegRepository {
    suspend fun registerUser(email: String, password: String): NetworkResult<Unit>
}
