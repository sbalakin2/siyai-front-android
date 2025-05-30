package com.example.siyai_front_android.presentation.auth.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.siyai_front_android.domain.dto.AuthProgress
import com.example.siyai_front_android.presentation.auth.email_confirmation.EmailConfirmationScreen
import com.example.siyai_front_android.presentation.auth.lets_meet.LetsMeetScreen
import com.example.siyai_front_android.presentation.auth.login.LoginScreen
import com.example.siyai_front_android.presentation.auth.onboarding.OnboardingScreen
import com.example.siyai_front_android.presentation.auth.password_recovery.PasswordRecovery1Screen
import com.example.siyai_front_android.presentation.auth.password_recovery.PasswordRecovery2Screen
import com.example.siyai_front_android.presentation.auth.password_reset.PasswordResetScreen
import com.example.siyai_front_android.presentation.auth.password_reset.SuccessPasswordResetScreen
import com.example.siyai_front_android.presentation.auth.reg.RegScreen

@Composable
fun AuthNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    enterToApp: (progress: AuthProgress) -> Unit,
    startDestination: AuthRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<AuthRoute.Onboarding> {

            val initialPage =
                it.toRoute<AuthRoute.Onboarding>().initialPage

            OnboardingScreen(
                onLoginClick = {
                    navController.navigate(AuthRoute.Login)
                },
                onRegClick = {
                    navController.navigate(AuthRoute.Reg)
                },
                initialPage = initialPage
            )
        }
        composable<AuthRoute.Login> {
            LoginScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onRegClick = {
                    navController.navigate(AuthRoute.Reg)
                },
                onPasswordRecoveryClick = {
                    navController.navigate(AuthRoute.RecoveryPassword)
                },
                onLoginSuccess = { email ->
                    enterToApp(AuthProgress.LogIn(email))
                },
                viewModelFactory = viewModelFactory
            )
        }
        composable<AuthRoute.Reg> {
            RegScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onLoginClick = {
                    navController.navigate(AuthRoute.Login)
                },
                onRegClick = { email, password, expDate, otp ->
                    navController.navigate(
                        AuthRoute.EmailConfirmation(
                            email = email,
                            password = password,
                            expDate = expDate,
                            otp = otp
                        )
                    )
                },
                viewModelFactory = viewModelFactory
            )
        }
        navigation<AuthRoute.RecoveryPassword>(startDestination = RecoveryPasswordRoute.RecoveryPassword1) {
            composable<RecoveryPasswordRoute.RecoveryPassword1> {
                PasswordRecovery1Screen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onRecoveryClick = {
                        navController.navigate(RecoveryPasswordRoute.RecoveryPassword2)
                    },
                    viewModelFactory = viewModelFactory
                )
            }
            composable<RecoveryPasswordRoute.RecoveryPassword2> {
                PasswordRecovery2Screen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable<AuthRoute.EmailConfirmation> {
            val emailConfirmation = it.toRoute<AuthRoute.EmailConfirmation>()

            EmailConfirmationScreen(
                email = emailConfirmation.email,
                password = emailConfirmation.password,
                expDate = emailConfirmation.expDate,
                otp = emailConfirmation.otp,
                onEmailConfirmationClick = {
                    enterToApp(AuthProgress.Register(emailConfirmation.email))

                    navController
                        .navigate(AuthRoute.LetsMeet(email = emailConfirmation.email)) {
                            popUpTo(AuthRoute.Reg) {
                                inclusive = false
                            }
                        }
                },
                onResendingCodeClick = {

                },
                viewModelFactory = viewModelFactory
            )
        }
        composable<AuthRoute.LetsMeet> {
            val letsMeetArgs = it.toRoute<AuthRoute.LetsMeet>()

            LetsMeetScreen(
                email = letsMeetArgs.email,
                onProfileCreated = {
                    enterToApp(AuthProgress.RegisterAndCreateProfile(letsMeetArgs.email))
                },
                viewModelFactory = viewModelFactory
            )
        }
        composable<AuthRoute.PasswordReset>(
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://com.example.siyai_front_android/password_reset?token={token}"
                }
            )
        ) {

            val token = it.toRoute<AuthRoute.PasswordReset>().token
            Log.d("MyLog","Navigated to PasswordResetScreen, token= $token")
            PasswordResetScreen(
                token = token,
                onBackClick = {
                    navController.popBackStack()
                },
                onSuccessPasswordReset = {
                    navController.navigate(AuthRoute.SuccessPasswordReset) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                viewModelFactory = viewModelFactory
            )
        }

        composable<AuthRoute.SuccessPasswordReset> {
            SuccessPasswordResetScreen(
                onLoginClick = { fromSuccessPasswordReset ->
                    navController.navigate(
                        AuthRoute.Onboarding(
                            initialPage = fromSuccessPasswordReset
                        )
                    ) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                    navController.navigate(AuthRoute.Login)
                }
            )
        }
    }
}
