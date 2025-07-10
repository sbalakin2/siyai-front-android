package com.example.siyai_front_android.presentation.auth.navigation

import com.example.siyai_front_android.utils.ONBOARDING_PAGE_ONE
import kotlinx.serialization.Serializable

sealed interface AuthRoute {
    @Serializable
    data class Onboarding(
        val initialPage: Int = ONBOARDING_PAGE_ONE
    ): AuthRoute

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

    @Serializable
    data object SuccessPasswordReset: AuthRoute
}

sealed class RecoveryPasswordRoute{

    @Serializable
    data object RecoveryPassword1: AuthRoute

    @Serializable
    data object RecoveryPassword2: AuthRoute
}
