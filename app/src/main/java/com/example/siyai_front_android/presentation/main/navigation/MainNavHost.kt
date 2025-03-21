package com.example.siyai_front_android.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.siyai_front_android.presentation.login.LoginScreen
import com.example.siyai_front_android.presentation.onboarding.OnboardingScreen
import com.example.siyai_front_android.presentation.password_recovery.PasswordRecovery1Screen
import com.example.siyai_front_android.presentation.password_recovery.PasswordRecovery2Screen
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
                },
                onPasswordRecoveryClick = {
                    navController.navigate(Route.RecoveryPassword)
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
        navigation<Route.RecoveryPassword>(startDestination = Route.RecoveryPassword1) {
            composable<Route.RecoveryPassword1> {
                PasswordRecovery1Screen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onRecoveryClick = {
                        navController.navigate(Route.RecoveryPassword2)
                    }
                )
            }
            composable<Route.RecoveryPassword2> {
                PasswordRecovery2Screen(
                    onBackClick = {
                        navController.navigate(Route.Onboarding) {
                            popUpTo(route = Route.RecoveryPassword) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
