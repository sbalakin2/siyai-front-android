package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.data.remote.dto.PasswordResetRequest
import com.example.siyai_front_android.domain.repositories.PasswordResetRepository
import com.example.siyai_front_android.utils.NetworkResult
import com.example.siyai_front_android.utils.parseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PasswordResetRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
): PasswordResetRepository {
    override suspend fun resetPassword(token: String, newPassword: String): NetworkResult<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val response = networkApi.resetPassword(
                    passwordResetRequest =
                        PasswordResetRequest(token = token, newPassword = newPassword)
                )
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    return@withContext NetworkResult.Success(Unit)
                } else {
                    val error = parseError(response)
                    return@withContext NetworkResult.Error(error.first, error.second)
                }
            } catch (e: Exception) {
                return@withContext NetworkResult.Exception(e)
            }
        }
}

