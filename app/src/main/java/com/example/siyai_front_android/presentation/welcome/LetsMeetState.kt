package com.example.siyai_front_android.presentation.welcome

sealed interface LetsMeetState {
    data object Success: LetsMeetState
    data class Error(val code: Int, val message: String) : LetsMeetState
    data class Exception(val message: String) : LetsMeetState
    data object Loading : LetsMeetState
}