package com.example.siyai_front_android.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DeleteResponse(
    val code: Int,
    val message: String
)
