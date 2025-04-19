package com.example.siyai_front_android.presentation.main.home_container.navigation

import kotlinx.serialization.Serializable

sealed interface HomeRoute {

    @Serializable
    data object Home
}