package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.Verification
import com.example.siyai_front_android.utils.NetworkResult

interface VerificationRepository {
    suspend fun verify(email: String): NetworkResult<Verification>
}
