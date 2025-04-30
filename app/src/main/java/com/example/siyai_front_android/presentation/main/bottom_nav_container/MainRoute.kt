package com.example.siyai_front_android.presentation.main.bottom_nav_container


import kotlinx.serialization.Serializable

sealed interface MainRoute{

    @Serializable
    data object Home: MainRoute

    @Serializable
    data object Training: MainRoute

    @Serializable
    data object Audio: MainRoute

    @Serializable
    data object Profile: MainRoute

    @Serializable
    data object ProfileEditing: MainRoute

    @Serializable
    data object SignOfTheDay: MainRoute
}

fun String.toMainRoute(): MainRoute {
    val route = this.split(".")
    return when(route.last()){
        "Home" -> MainRoute.Home
        "Training" -> MainRoute.Training
        "Audio" -> MainRoute.Audio
        "Profile" -> MainRoute.Profile
        "ProfileEditing" -> MainRoute.ProfileEditing
        "SignOfTheDay" -> MainRoute.SignOfTheDay
        else -> throw IllegalArgumentException("Unknown MainRoute: $route")
    }
}
