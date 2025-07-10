package com.example.siyai_front_android.presentation.profile_editing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.siyai_front_android.domain.dto.CountryWithCities

@Stable
class CountriesAndCitiesState(
   countryWithCities: List<CountryWithCities>
) {
    var countries by mutableStateOf<List<CountryWithCities>>(countryWithCities)
    var cities by mutableStateOf<List<String>>(emptyList())

    fun setCitiesFromCountry(index: Int) {
        cities = countries.getOrNull(index)?.cities.orEmpty()
    }
}

@Composable
fun rememberCountryAndCitiesState(
    countryWithCities: List<CountryWithCities>
): CountriesAndCitiesState {
    return remember(countryWithCities) { CountriesAndCitiesState(countryWithCities) }
}