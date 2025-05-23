package com.example.siyai_front_android.data.remote

import com.example.siyai_front_android.data.remote.dto.ProfileResponse
import com.example.siyai_front_android.data.remote.dto.LoginRequest
import com.example.siyai_front_android.data.remote.dto.PasswordResetRequest
import com.example.siyai_front_android.data.remote.dto.ProfileRequest
import com.example.siyai_front_android.data.remote.dto.RecoveryPasswordRequest
import com.example.siyai_front_android.data.remote.dto.VerificationResponse
import com.example.siyai_front_android.data.remote.dto.RegRequest
import com.example.siyai_front_android.data.remote.dto.VerificationRequest
import com.example.siyai_front_android.data.remote.dto.UserProfileRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface NetworkApi {
    @POST("user-service/v1/registration")
    suspend fun registerUser(
        @Body regRequest: RegRequest
    ): Response<Unit>

    @POST("/user-service/v1/sign-in")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<Unit>

    @POST("/user-service/v2/forgot-password")
    suspend fun recoveryPassword(
        @Body recoveryPasswordRequest: RecoveryPasswordRequest
    ): Response<Unit>

    @POST("message-service/v1/otp")
    suspend fun verify(
        @Body verificationRequest: VerificationRequest
    ): Response<VerificationResponse>

    @POST("/user-service/v1/create-profile")
    suspend fun createUserProfile(
        @Body userProfileRequest: UserProfileRequest
    ): Response<Unit>

    @PATCH("/user-service/v1/edit-profile")
    suspend fun editProfile(
        @Body userProfileRequest: UserProfileRequest
    ): Response<Unit>

    @POST("/user-service/v1/info")
    suspend fun getProfile(
        @Body profileRequest: ProfileRequest
    ): Response<ProfileResponse>

    @POST("/user-service/v2/reset-password")
    suspend fun resetPassword(
        @Body passwordResetRequest: PasswordResetRequest
    ): Response<Unit>
}