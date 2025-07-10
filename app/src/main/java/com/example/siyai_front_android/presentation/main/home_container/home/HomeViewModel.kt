package com.example.siyai_front_android.presentation.main.home_container.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.GetProfileUseCase
import com.example.siyai_front_android.presentation.profile.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
): ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        getProfile()
    }

    private fun getProfile() {
        viewModelScope.launch {
            getProfileUseCase()
                .collect(::updateHomeState)
        }
    }

    private fun updateHomeState(state:  ProfileState) {
        _homeState.value = when (state) {
            is ProfileState.Success -> {
                HomeState.Success(state.profile)
            }
            is ProfileState.Error -> {
                HomeState.Error(state.code, state.message)
            }
            is ProfileState.Exception -> {
                HomeState.Exception(state.message)
            }
            ProfileState.Loading -> {
                HomeState.Loading
            }
        }
    }
}