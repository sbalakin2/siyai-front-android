package com.example.siyai_front_android.domain.dto

data class CreateProfileData(
    val email: String,
    val name: String,
    val surName: String,
    val birthday: String,
    val country: String,
    val city: String
)