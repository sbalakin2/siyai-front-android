package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.CountryWithCities

interface GetCountiesWithCitiesUseCase {
    suspend operator fun invoke(): List<CountryWithCities>
}