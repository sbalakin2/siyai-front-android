package com.example.siyai_front_android.presentation.main.home_container.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.siyai_front_android.presentation.model.Product

@Composable
fun HomeContainer(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    navigateToSignOfTheDayScreen: () -> Unit,
    navigateToProductDetailScreen: (Product) -> Unit,
    navigateToFreeLessonsScreen: () -> Unit
) {
    val navController = rememberNavController()

    HomeNavHost(
        navController = navController,
        modifier = modifier,
        viewModelFactory = viewModelFactory,
        navigateToSignOfTheDayScreen = navigateToSignOfTheDayScreen,
        navigateToProductDetailScreen = navigateToProductDetailScreen,
        navigateToFreeLessonsScreen = navigateToFreeLessonsScreen
    )
}