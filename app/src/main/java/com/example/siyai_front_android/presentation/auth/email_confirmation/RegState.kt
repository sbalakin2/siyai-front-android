package com.example.siyai_front_android.presentation.auth.email_confirmation

sealed interface RegState {
    data object Success : RegState
    data class Error(val code: Int, val message: String) : RegState
    data class Exception(val message: String) : RegState
    data object Idle : RegState
}
