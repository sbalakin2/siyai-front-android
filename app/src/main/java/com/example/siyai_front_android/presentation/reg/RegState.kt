package com.example.siyai_front_android.presentation.reg

sealed interface RegState {
    data object Success : RegState
    data class Error(val code: Int, val message: String) : RegState
    data class Exception(val message: String) : RegState
    data object Loading : RegState
}
