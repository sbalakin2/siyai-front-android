package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.utils.NetworkResult

interface RecoveryPasswordRepository {
    suspend fun recoveryPassword(email: String): NetworkResult<Unit>
}