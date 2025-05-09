package com.example.siyai_front_android.presentation.siyai_container.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siyai_front_android.SiyaiViewModel
import com.example.siyai_front_android.presentation.auth.navigation.AuthScreen
import com.example.siyai_front_android.presentation.main.bottom_nav_container.MainContainer

@Composable
fun SiyaiNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: SiyaiViewModel = viewModel(factory = viewModelFactory)
    val rootDestination by viewModel.startDestination.collectAsStateWithLifecycle()

    rootDestination?.let { startDestination ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            // Онбоардинг и авторизация/регистрация
            composable<Route.Auth> {
                AuthScreen(
                    enterToApp = viewModel::setAuthProgress,
                    viewModelFactory = viewModelFactory
                )
            }
            // Основное приложение
            composable<Route.Main> {
                MainContainer(
                    exitFromApp = viewModel::exitFromApp,
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}

