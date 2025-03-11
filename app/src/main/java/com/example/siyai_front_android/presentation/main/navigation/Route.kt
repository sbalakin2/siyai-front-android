package com.example.siyai_front_android.presentation.main.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Onboarding: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object Reg: Route
}
