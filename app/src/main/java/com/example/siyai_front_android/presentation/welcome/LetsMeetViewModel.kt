package com.example.siyai_front_android.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.model.UserProfileData
import com.example.siyai_front_android.domain.usecases.LetsMeetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LetsMeetViewModel @Inject constructor(
    private val letsMeetUseCase: LetsMeetUseCase
) : ViewModel() {

    private val _letsMeetState = MutableStateFlow<LetsMeetState>(LetsMeetState.Nothing)
    val letsMeetState: StateFlow<LetsMeetState> = _letsMeetState.asStateFlow()

    private val _countries = MutableStateFlow(
        listOf(CountrySelectItem(label = "Россия", value = "RUSSIA"))
    )
    val counties = _countries.asStateFlow()

    private val _cities = MutableStateFlow(
        listOf(CitySelectItem(label = "Москва", value = "MOSCOW"))
    )
    val cities = _cities.asStateFlow()

    fun createUserProfile(data: UserProfileData) {
        viewModelScope.launch {
            _letsMeetState.value = LetsMeetState.Loading
            _letsMeetState.value = runCatching { letsMeetUseCase(data) }
                .getOrElse { LetsMeetState.Exception(it.localizedMessage ?: "Unknown error") }
        }
    }
}