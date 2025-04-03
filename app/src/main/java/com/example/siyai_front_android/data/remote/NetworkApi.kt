package com.example.siyai_front_android.data.remote

import com.example.siyai_front_android.data.remote.dto.LoginRequest
import com.example.siyai_front_android.data.remote.dto.RegRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkApi {
    @POST("/user-service/v1/registration")
    suspend fun registerUser(
        @Header("Authorization") authorization: String,
        @Body regRequest: RegRequest
    ): Response<Unit>

    @POST("auth_server/v1/sign-in")
    suspend fun loginUser(
        @Header("Authorization") authorization: String,
        @Body loginRequest: LoginRequest
    ): Response<Unit>
}