package com.example.siyai_front_android.presentation.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.siyai_front_android.presentation.main.navigation.MainNavHost

@Composable
fun MainScreen(
    viewModelFactory: ViewModelProvider.Factory
) {
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0 , 0, 0)
    ) { paddingValues ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            viewModelFactory = viewModelFactory
        )
    }
}
