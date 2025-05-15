package com.example.siyai_front_android.domain.dto

sealed interface AuthProgress {

    data class LogIn(val email: String): AuthProgress

    data class Register(val email: String): AuthProgress

    data class RegisterAndCreateProfile(val email: String): AuthProgress
}