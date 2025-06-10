package com.example.siyai_front_android.data.mappers

import com.example.siyai_front_android.data.remote.dto.ProfileResponse
import com.example.siyai_front_android.domain.dto.Profile

fun ProfileResponse.toProfileInfo() = Profile(
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    birthday = birthday.orEmpty(),
    email = email.orEmpty(),
    country = country.orEmpty(),
    city = city.orEmpty(),
    photo = "photoFromResponse" //временное решение
)