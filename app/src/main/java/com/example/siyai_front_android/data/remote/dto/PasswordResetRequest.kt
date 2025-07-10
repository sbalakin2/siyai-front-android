package com.example.siyai_front_android.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PasswordResetRequest(
    val token: String,
    @SerialName("new_password") val newPassword: String
)