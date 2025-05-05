package com.example.siyai_front_android.domain.dto

sealed interface AuthProgress {

    data object LogIn: AuthProgress

    data object Register: AuthProgress

    data object RegisterAndCreateProfile: AuthProgress
}