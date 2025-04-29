package com.example.siyai_front_android.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileRequest(
    val email: String,
    val firstname: String,
    val lastname: String,
    val birthday: String,
    val country: String,
    val city: String
)