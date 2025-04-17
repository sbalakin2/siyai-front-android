package com.example.siyai_front_android.presentation.main.home_container.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeContainer(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory
) {
    val navController = rememberNavController()

    HomeNavHost(
        navController = navController,
        modifier = modifier,
        viewModelFactory = viewModelFactory
    )
}