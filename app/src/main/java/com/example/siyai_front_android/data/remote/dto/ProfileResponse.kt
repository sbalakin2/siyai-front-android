package com.example.siyai_front_android.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName ("firstname") val firstName: String?,
    @SerialName ("lastname") val lastName: String?,
    val birthday: String?,
    val email: String?,
    val country: String?,
    val city: String?
)