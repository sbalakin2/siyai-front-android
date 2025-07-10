package com.example.siyai_front_android.presentation.siyai_container.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Auth: Route

    @Serializable
    data object Main: Route
}
