package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.CountryWithCities
import com.example.siyai_front_android.domain.repositories.CountryWithCitiesRepository
import javax.inject.Inject

class GetCountiesWithCitiesUseCaseImpl @Inject constructor(
    private val repository: CountryWithCitiesRepository
) : GetCountiesWithCitiesUseCase {
    override suspend fun invoke(): List<CountryWithCities> = repository.getAllCountries()
}