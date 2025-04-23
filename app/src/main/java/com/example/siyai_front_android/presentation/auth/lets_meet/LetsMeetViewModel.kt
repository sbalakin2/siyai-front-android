package com.example.siyai_front_android.presentation.auth.lets_meet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.dto.CountryWithCities
import com.example.siyai_front_android.domain.dto.UserProfileData
import com.example.siyai_front_android.domain.usecases.CreateProfileUseCase
import com.example.siyai_front_android.domain.usecases.GetCountiesWithCitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LetsMeetViewModel @Inject constructor(
    private val getCountiesWithCitiesUseCase: GetCountiesWithCitiesUseCase,
    private val createProfileUseCase: CreateProfileUseCase
) : ViewModel() {

    private val _letsMeetState = MutableStateFlow<LetsMeetState>(LetsMeetState.Nothing)
    val letsMeetState: StateFlow<LetsMeetState> = _letsMeetState.asStateFlow()

    private val _countriesWithCities = MutableStateFlow<List<CountryWithCities>>(emptyList())
    val countiesWithCities = _countriesWithCities.asStateFlow()

    init {
        loadCountriesWithCities()
    }

    private fun loadCountriesWithCities() {
        viewModelScope.launch {
            _countriesWithCities.value = getCountiesWithCitiesUseCase()
        }
    }

    fun createUserProfile(data: UserProfileData) {
        viewModelScope.launch {
            _letsMeetState.value = LetsMeetState.Loading
            _letsMeetState.value = runCatching { createProfileUseCase(data) }
                .getOrElse { LetsMeetState.Exception(it.localizedMessage ?: "Unknown error") }
        }
    }
}