package com.example.siyai_front_android.presentation.main.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Onboarding: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object Reg: Route

    @Serializable
    data object RecoveryPassword

    @Serializable
    data object RecoveryPassword1: Route

    @Serializable
    data object RecoveryPassword2: Route

    @Serializable
    data object LetsMeet: Route

    @Serializable
    data class EmailConfirmation(
        val email: String,
        val password: String,
        val expDate: Int,
        val otp: Int
    ): Route

    @Serializable
    data object Profile: Route
}
