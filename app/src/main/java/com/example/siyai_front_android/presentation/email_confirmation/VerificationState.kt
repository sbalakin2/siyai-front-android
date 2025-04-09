package com.example.siyai_front_android.presentation.email_confirmation

sealed interface VerificationState {
    data class Success(val expDate: Int, val otp: Int) : VerificationState
    data class Error(val code: Int, val message: String) : VerificationState
    data class Exception(val message: String) : VerificationState
    data object Idle : VerificationState
}
