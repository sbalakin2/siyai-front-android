package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.data.remote.dto.UserProfileRequest
import com.example.siyai_front_android.domain.repositories.ProfileEditingRepository
import com.example.siyai_front_android.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class ProfileEditingRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : ProfileEditingRepository {
    override suspend fun editProfile(
        email: String,
        firstName: String,
        lastName: String,
        birthday: String,
        country: String,
        city: String
    ): NetworkResult<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = networkApi.editProfile(
                userProfileRequest = UserProfileRequest(
                    email = email,
                    firstname = firstName,
                    lastname = lastName,
                    birthday = birthday,
                    country = country,
                    city = city
                )
            )
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return@withContext NetworkResult.Success(Unit)
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