package com.example.siyai_front_android.presentation.profile

import com.example.siyai_front_android.domain.dto.Profile

sealed interface ProfileState {
    data class Success(val profile: Profile) : ProfileState
    data class Error(val code: Int, val message: String) : ProfileState
    data class Exception(val message: String) : ProfileState
    data object Loading : ProfileState
}