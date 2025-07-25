package com.example.siyai_front_android.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerificationResponse(
    @SerialName ("expirationTime") val expDate: Int?,
    @SerialName ("code") val code: Int?
)
