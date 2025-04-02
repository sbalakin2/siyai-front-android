package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.data.remote.NetworkApi
import com.example.siyai_front_android.data.remote.dto.LoginRequest
import com.example.siyai_front_android.domain.repositories.LoginRepository
import com.example.siyai_front_android.utils.NetworkResult
import com.example.siyai_front_android.utils.createCredentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : LoginRepository{

    override suspend fun loginUser(email: String, password: String): NetworkResult<Unit> =
        withContext(Dispatchers.IO){
            try {
                val response = networkApi.loginUser(
                    authorization = createCredentials(email, password),
                    loginRequest = LoginRequest(email = email, password = password)
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