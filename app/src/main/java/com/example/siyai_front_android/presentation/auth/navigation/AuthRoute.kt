package com.example.siyai_front_android.presentation.auth.navigation

import kotlinx.serialization.Serializable

sealed interface AuthRoute {
    @Serializable
    data object Onboarding: AuthRoute

    @Serializable
    data object Login: AuthRoute

    @Serializable
    data object Reg: AuthRoute

    @Serializable
    data object RecoveryPassword: AuthRoute

    @Serializable
    data class EmailConfirmation(
        val email: String,
        val password: String,
        val expDate: Int,
        val otp: Int
    ): AuthRoute

    @Serializable
    data class LetsMeet(val email: String): AuthRoute

    @Serializable
    data class PasswordReset(
        val token: String
    ): AuthRoute
}

sealed class RecoveryPasswordRoute{

    @Serializable
    data object RecoveryPassword1: AuthRoute

    @Serializable
    data object RecoveryPassword2: AuthRoute
}
