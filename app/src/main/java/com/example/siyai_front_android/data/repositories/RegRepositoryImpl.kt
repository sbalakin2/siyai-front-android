package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.data.remote.dto.RegRequest
import com.example.siyai_front_android.domain.repositories.RegRepository
import com.example.siyai_front_android.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
): RegRepository {
    override suspend fun registerUser(email: String, password: String): NetworkResult<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val response = networkApi.registerUser(
                    regRequest = RegRequest(email = email, password = password)
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
