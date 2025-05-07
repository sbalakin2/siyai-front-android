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
    data class ProfileEditing(
        val email: String,
        val firstName: String,
        val lastName: String,
        val birthday: String,
        val country: String,
        val city: String
    ): MainRoute

    @Serializable
    data object SignOfTheDay: MainRoute
}

fun String.toMainRoute(): MainRoute? {
    val route = this.split(".")
    return when(route.last()){
        "Home" -> MainRoute.Home
        "Training" -> MainRoute.Training
        "Audio" -> MainRoute.Audio
        "Profile" -> MainRoute.Profile
        "SignOfTheDay" -> MainRoute.SignOfTheDay
        else -> null
    }
}
