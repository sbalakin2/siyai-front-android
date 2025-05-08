package com.example.siyai_front_android.presentation.main.bottom_nav_container

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.siyai_front_android.presentation.main.home_container.navigation.HomeContainer
import com.example.siyai_front_android.presentation.profile.ProfileScreen
import com.example.siyai_front_android.presentation.profile_editing.ProfileEditingScreen
import com.example.siyai_front_android.presentation.sign_day.SignOfTheDayScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    exitFromApp: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        modifier = modifier,
    ) {
        composable<MainRoute.Home> {
            HomeContainer(
                viewModelFactory = viewModelFactory,
                navigateToSignOfTheDayScreen = {
                    navController.navigate(MainRoute.SignOfTheDay)
                }
            )
        }
        composable<MainRoute.Training> {}
        composable<MainRoute.Audio> {}
        composable<MainRoute.Profile> {
            ProfileScreen(
                onEditClick = { email, firstName, lastName, birthday, country, city ->
                    navController.navigate(
                        MainRoute.ProfileEditing(
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            birthday = birthday,
                            country = country,
                            city = city
                        )
                    )
                },
                viewModelFactory = viewModelFactory,
                onExitClick = exitFromApp
            )
        }
        composable<MainRoute.ProfileEditing> {
            val profileEditing = it.toRoute<MainRoute.ProfileEditing>()
            ProfileEditingScreen(
                email = profileEditing.email,
                onBackClick = {
                    navController.popBackStack()
                },
                onOnboardingClick = {

                },
                viewModelFactory = viewModelFactory
            )
        }
        composable<MainRoute.SignOfTheDay> {
            SignOfTheDayScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}
