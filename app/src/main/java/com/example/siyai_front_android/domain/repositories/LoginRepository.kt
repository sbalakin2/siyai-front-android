package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.utils.NetworkResult

interface LoginRepository {
    suspend fun loginUser(email: String, password: String): NetworkResult<Unit>
}