package com.example.siyai_front_android.presentation.auth.password_reset

sealed interface PasswordResetState {
    data object Success : PasswordResetState
    data class Error(val code: Int, val message: String) : PasswordResetState
    data class Exception(val message: String) : PasswordResetState
    data object Idle : PasswordResetState
}