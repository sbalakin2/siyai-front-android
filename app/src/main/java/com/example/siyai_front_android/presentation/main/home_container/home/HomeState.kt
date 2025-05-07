package com.example.siyai_front_android.presentation.main.home_container.home

import com.example.siyai_front_android.domain.dto.Profile

sealed interface HomeState {
    data class Success(val profile: Profile) : HomeState
    data class Error(val code: Int, val message: String) : HomeState
    data class Exception(val message: String) : HomeState
    data object Loading : HomeState
}