package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.domain.repositories.PasswordResetRepository
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class PasswordResetRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
): PasswordResetRepository {
    override suspend fun resetPassword(token: String, newPassword: String): NetworkResult<Unit> =
        NetworkResult.Success(Unit)
}