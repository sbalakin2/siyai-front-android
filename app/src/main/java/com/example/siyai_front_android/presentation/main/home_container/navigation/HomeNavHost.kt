package com.example.siyai_front_android.presentation.main.home_container.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siyai_front_android.presentation.main.home_container.home.HomeScreen


@Composable
fun HomeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    navigateToSignOfTheDayScreen: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute.Home,
        modifier = modifier,
    ) {
        composable<HomeRoute.Home> {
            HomeScreen(
                viewModelFactory = viewModelFactory,
                navigateToSignOfTheDayScreen = navigateToSignOfTheDayScreen,
                navigateToArchiveScreen = {},
                navigateToAddTrackScreen = {},
                navigateToFreeLessonsScreen = {},
                navigateToWaitingListScreen = {}
            )
        }
    }
}
