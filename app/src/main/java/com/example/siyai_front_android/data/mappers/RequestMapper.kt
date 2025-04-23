package com.example.siyai_front_android.data.mappers

import com.example.siyai_front_android.data.remote.dto.UserProfileRequest
import com.example.siyai_front_android.domain.dto.UserProfileData

fun UserProfileData.toCreateProfileRequest(): UserProfileRequest {
    return UserProfileRequest(
        email = email,
        firstname = name,
        lastname = surName,
        birthday = birthday,
        country = country,
        city = city
    )
}