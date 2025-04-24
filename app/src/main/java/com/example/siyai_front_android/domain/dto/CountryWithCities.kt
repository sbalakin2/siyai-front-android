package com.example.siyai_front_android.domain.dto

data class CountryWithCities(
    val id: Int,
    val name: String,
    val cities: List<String>
)