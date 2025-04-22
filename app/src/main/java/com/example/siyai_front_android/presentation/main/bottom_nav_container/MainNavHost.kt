package com.example.siyai_front_android.presentation.main.bottom_nav_container

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siyai_front_android.presentation.main.home_container.navigation.HomeContainer
import com.example.siyai_front_android.presentation.profile.ProfileScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        modifier = modifier,
    ) {
        composable<MainRoute.Home> {
            HomeContainer(
                viewModelFactory = viewModelFactory
            )
        }
        composable<MainRoute.Training> {}
        composable<MainRoute.Audio> {}
        composable<MainRoute.Profile> {
            ProfileScreen()
        }
    }
}
