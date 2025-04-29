package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.CountryWithCities

interface CountryWithCitiesRepository {
    fun getAllCountries(): List<CountryWithCities>
}