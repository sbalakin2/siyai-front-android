package com.example.siyai_front_android.presentation.main.bottom_nav_container


import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

sealed interface MainRoute {

    @Serializable
    data object Home: MainRoute

    @Serializable
    data object Training: MainRoute

    @Serializable
    data object Audio: MainRoute

    @Serializable
    data object MyState: MainRoute

    @Serializable
    data object Last3Cycles: MainRoute

    @Serializable
    data object CalendarWithInfo: MainRoute

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

    @Serializable
    data class ProductDetail(
        @DrawableRes val imageId: Int,
        val name: String,
        val price: Long
    ): MainRoute

    @Serializable
    data object FreeLessons: MainRoute

    @Serializable
    data object FreeLessonDetail: MainRoute

    @Serializable
    data object AudioPlay: MainRoute

    @Serializable
    data object VideoPlay: MainRoute
}

fun String.toMainRoute(): MainRoute? {
    val route = this.split(".")
    return when(route.last()){
        "Home" -> MainRoute.Home
        "Training" -> MainRoute.Training
        "Audio" -> MainRoute.Audio
        "MyState" -> MainRoute.MyState
        "Profile" -> MainRoute.Profile
        "SignOfTheDay" -> MainRoute.SignOfTheDay
        else -> null
    }
}
