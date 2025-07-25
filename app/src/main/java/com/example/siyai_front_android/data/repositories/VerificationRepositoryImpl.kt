package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.mappers.toVerification
import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.data.remote.dto.VerificationRequest
import com.example.siyai_front_android.domain.dto.Verification
import com.example.siyai_front_android.domain.repositories.VerificationRepository
import com.example.siyai_front_android.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class VerificationRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
): VerificationRepository {
    override suspend fun verify(email: String): NetworkResult<Verification> =
        withContext(Dispatchers.IO) {
            try {
                val response = networkApi.verify(
                    verificationRequest = VerificationRequest(email)
                )
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    return@withContext NetworkResult.Success(body.toVerification())
                } else {
                    val errorBody = response.errorBody()?.string().orEmpty()
                    val json = JSONObject(errorBody)
                    val code = json.optInt("code")
                    val message = json.optString("message")
                    return@withContext NetworkResult.Error(code, message)
                }
            } catch (e: Exception) {
                return@withContext NetworkResult.Exception(e)
            }
    }
}
