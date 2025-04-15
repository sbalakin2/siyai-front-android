package com.example.siyai_front_android.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VerificationRequest(
    val email: String
)
