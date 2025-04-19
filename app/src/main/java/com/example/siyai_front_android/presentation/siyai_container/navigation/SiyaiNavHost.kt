package com.example.siyai_front_android.presentation.siyai_container.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siyai_front_android.presentation.auth.navigation.AuthScreen
import com.example.siyai_front_android.presentation.main.bottom_nav_container.MainContainer
import com.example.siyai_front_android.presentation.splash.SplashScreen

@Composable
fun SiyaiNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory
) {
    NavHost(
        navController = navController,
        startDestination = Route.Splash,
        modifier = modifier,
    ) {
        composable<Route.Splash> {
            SplashScreen(
                navigateToAuth = {
                    navController.navigate(Route.Auth)
                },
                navigateToHome = {
                    navController.navigate(Route.Main)
                },
                viewModelFactory = viewModelFactory
            )
        }
        composable<Route.Auth> {
            AuthScreen(
                navigateToHome = {
                    navController.popBackStack(Route.Splash, inclusive = false)
                },
                viewModelFactory = viewModelFactory
            )
        }
        composable<Route.Main> {
            MainContainer(viewModelFactory = viewModelFactory)
        }
    }
}
