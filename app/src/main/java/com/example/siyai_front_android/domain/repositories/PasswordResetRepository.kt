package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.utils.NetworkResult

interface PasswordResetRepository {
    suspend fun resetPassword(token: String, newPassword: String): NetworkResult<Unit>
}