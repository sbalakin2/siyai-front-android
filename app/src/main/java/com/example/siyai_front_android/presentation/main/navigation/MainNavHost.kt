package com.example.siyai_front_android.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siyai_front_android.presentation.login.LoginScreen
import com.example.siyai_front_android.presentation.onboarding.OnboardingScreen
import com.example.siyai_front_android.presentation.reg.RegScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Onboarding,
        modifier = modifier,
    ) {
        composable<Route.Onboarding> {
            OnboardingScreen(
                onLoginClick = {
                    navController.navigate(Route.Login)
                },
                onRegClick = {
                    navController.navigate(Route.Reg)
                }
            )
        }
        composable<Route.Login> {
            LoginScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onRegClick = {
                    navController.navigate(Route.Reg)
                }
            )
        }
        composable<Route.Reg> {
            RegScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLoginClick = {
                    navController.navigate(Route.Login)
                }
            )
        }
    }
}
