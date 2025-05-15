package com.example.siyai_front_android.presentation.profile_editing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.siyai_front_android.domain.dto.CountryWithCities
import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.utils.parseISODate
import java.util.Date

@Stable
class ProfileEditState {
    var email by mutableStateOf("")
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var birthday by mutableStateOf<Date?>(null)

    var countryIndex by mutableIntStateOf(-1)
        private set

    var cityIndex by mutableIntStateOf(-1)
        private set

    fun updateCountryIndex(index: Int) {
        countryIndex = index
        cityIndex = -1
    }

    fun updateCityIndex(index: Int) {
        cityIndex = index
    }

    override fun toString(): String {
        return "ProfileState(firstName='$firstName', lastName='$lastName', birthday=$birthday, countyIndex=$countryIndex, cityIndex=$cityIndex)"
    }

    companion object {
        fun Saver(): Saver<ProfileEditState, *> = listSaver(
            save = {
                listOf(
                    it.email,
                    it.firstName,
                    it.lastName,
                    it.birthday,
                    it.countryIndex,
                    it.cityIndex
                )
            },
            restore = {
                ProfileEditState().apply {
                    email = it[0] as String
                    firstName = it[1] as String
                    lastName = it[2] as String
                    birthday = it[3] as Date?
                    countryIndex = it[4] as Int
                    cityIndex = it[5] as Int
                }
            }
        )
    }
}


@Composable
fun rememberProfileState(
    initialProfile: Profile?,
    countryWithCities: List<CountryWithCities>
): ProfileEditState {
    var isInitialized by rememberSaveable { mutableStateOf(false) }
    val state = rememberSaveable(saver = ProfileEditState.Saver()) { ProfileEditState() }

    LaunchedEffect(initialProfile, countryWithCities) {
        if (isInitialized) return@LaunchedEffect
        if (initialProfile == null || countryWithCities.isEmpty()) return@LaunchedEffect

        state.email = initialProfile.email
        state.firstName = initialProfile.firstName
        state.lastName = initialProfile.lastName
        state.birthday = runCatching { initialProfile.birthday.parseISODate() }.getOrNull()

        val country = countryWithCities.indexOfFirst { it.name == initialProfile.country }
        state.updateCountryIndex(country)

        val cities = countryWithCities.getOrNull(country)?.cities.orEmpty()
        val city = cities.indexOfFirst { it == initialProfile.city }
        state.updateCityIndex(city)

        isInitialized = true
    }

    return state
}