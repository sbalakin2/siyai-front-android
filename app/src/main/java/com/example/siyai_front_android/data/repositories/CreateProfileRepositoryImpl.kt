package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.mappers.toCreateProfileRequest
import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.domain.dto.CreateProfileData
import com.example.siyai_front_android.domain.repositories.CreateProfileRepository
import com.example.siyai_front_android.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateProfileRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : CreateProfileRepository {
    override suspend fun createUserProfile(data: CreateProfileData): NetworkResult<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val response = networkApi.createUserProfile(
                    userProfileRequest = data.toCreateProfileRequest()
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