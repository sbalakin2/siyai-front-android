package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.data.remote.dto.RecoveryPasswordRequest
import com.example.siyai_front_android.domain.repositories.RecoveryPasswordRepository
import com.example.siyai_front_android.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecoveryPasswordRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : RecoveryPasswordRepository {

    override suspend fun recoveryPassword(email: String): NetworkResult<Unit> =
        withContext(Dispatchers.IO){
            try {
                val response = networkApi.recoveryPassword(
                    recoveryPasswordRequest = RecoveryPasswordRequest(email = email)
                )
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    return@withContext NetworkResult.Success(Unit)
                } else {
                    return@withContext NetworkResult.Error(response.code(), response.message())
                }
            } catch (e: Exception) {
                return@withContext NetworkResult.Exception(e)
            }
        }
}