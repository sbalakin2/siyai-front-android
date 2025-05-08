package com.example.siyai_front_android.presentation.profile_editing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.dto.CountryWithCities
import com.example.siyai_front_android.domain.dto.UserProfileData
import com.example.siyai_front_android.domain.usecases.EditProfileUseCase
import com.example.siyai_front_android.domain.usecases.GetCountiesWithCitiesUseCase
import com.example.siyai_front_android.domain.usecases.GetUserProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileEditingViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
    private val getCountiesWithCitiesUseCase: GetCountiesWithCitiesUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _profileEditingState = MutableStateFlow<ProfileEditingState>(ProfileEditingState.Idle)
    val profileEditingState: StateFlow<ProfileEditingState> = _profileEditingState.asStateFlow()

    private val _countriesWithCities = MutableStateFlow<List<CountryWithCities>>(emptyList())
    val countiesWithCities = _countriesWithCities.asStateFlow()

    private val _initialUserProfile = MutableStateFlow<UserProfileData?>(null)
    val initialUserProfile: StateFlow<UserProfileData?> = _initialUserProfile.asStateFlow()

    init {
        loadInitialProfileData()
        loadCountriesWithCities()
    }

    private fun loadCountriesWithCities() {
        viewModelScope.launch(Dispatchers.IO) {
            _countriesWithCities.value = getCountiesWithCitiesUseCase()
        }
    }

    private fun loadInitialProfileData() {
        viewModelScope.launch {
            _initialUserProfile.value = getUserProfileUseCase()
                .fold(onSuccess = { it }, onFailure = { getEmptyUserProfileData() })
        }
    }

    fun editProfile(
        email: String,
        firstName: String,
        lastName: String,
        birthday: String,
        country: String,
        city: String
    ) {
        viewModelScope.launch {
            _profileEditingState.value = editProfileUseCase(
                email = email,
                firstName = firstName,
                lastName = lastName,
                birthday = birthday,
                country = country,
                city = city
            )
        }
    }

    fun clearProfileEditingState() {
        _profileEditingState.value = ProfileEditingState.Idle
    }

    private fun getEmptyUserProfileData(): UserProfileData {
        return UserProfileData(
            email = "",
            name = "",
            surName = "",
            birthday = "",
            country = "",
            city = ""
        )
    }
}