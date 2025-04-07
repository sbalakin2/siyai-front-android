package com.example.siyai_front_android.presentation.login

sealed interface LoginState {
    data object Success : LoginState
    data class Error(val code: Int, val message: String) : LoginState
    data class Exception(val message: String) : LoginState
    data object Loading : LoginState
    data object Empty : LoginState
}