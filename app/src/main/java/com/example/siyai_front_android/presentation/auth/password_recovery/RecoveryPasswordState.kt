package com.example.siyai_front_android.presentation.auth.password_recovery

sealed interface RecoveryPasswordState {
    data object Success : RecoveryPasswordState
    data class Error(val code: Int, val message: String) : RecoveryPasswordState
    data class Exception(val message: String) : RecoveryPasswordState
    data object Loading : RecoveryPasswordState
    data object Empty : RecoveryPasswordState
}