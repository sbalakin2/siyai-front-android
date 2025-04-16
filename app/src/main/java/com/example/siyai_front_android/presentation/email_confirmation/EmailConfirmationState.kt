package com.example.siyai_front_android.presentation.email_confirmation

sealed interface EmailConfirmationState {
    data object RegSuccess : EmailConfirmationState
    data class VerificationSuccess(val expDate: Int, val otp: Int) : EmailConfirmationState
    data class Error(val code: Int, val message: String) : EmailConfirmationState
    data class Exception(val message: String) : EmailConfirmationState
    data object Idle : EmailConfirmationState
}