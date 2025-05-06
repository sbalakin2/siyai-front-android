package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.mappers.toProfileInfo
import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.domain.repositories.ProfileRepository
import com.example.siyai_front_android.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
): ProfileRepository {
    override suspend fun getProfile(email: String): NetworkResult<Profile> =
        withContext(Dispatchers.IO) {
            try {
                val response = networkApi.getProfile(email)
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    return@withContext NetworkResult.Success(body.toProfileInfo())
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