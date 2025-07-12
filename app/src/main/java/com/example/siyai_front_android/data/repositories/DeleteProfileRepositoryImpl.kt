package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.local.MyStateDao
import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.data.remote.dto.DeleteRequest
import com.example.siyai_front_android.domain.repositories.DeleteProfileRepository
import com.example.siyai_front_android.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteProfileRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val dao: MyStateDao
) : DeleteProfileRepository {

    override suspend fun deleteProfile(email: String): NetworkResult<String> =
        withContext(Dispatchers.IO) {
            try {
                val response = networkApi.deleteProfile(
                    deleteRequest = DeleteRequest(email = email)
                )
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    dao.deleteUserState(email)
                    return@withContext NetworkResult.Success(body.message)
                } else {
                    return@withContext NetworkResult.Error(response.code(), response.message())
                }
            } catch (e: Exception) {
                return@withContext NetworkResult.Exception(e)
            }
        }
}
