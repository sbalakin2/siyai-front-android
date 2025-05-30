package com.example.siyai_front_android.presentation.siyai_container

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.siyai_front_android.presentation.auth.navigation.AuthRoute
import com.example.siyai_front_android.presentation.siyai_container.navigation.SiyaiNavHost

@Composable
fun SiyaiContainer(
    viewModelFactory: ViewModelProvider.Factory,
    startDestination: AuthRoute
) {
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0 , 0, 0)
    ) { paddingValues ->
        SiyaiNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            viewModelFactory = viewModelFactory,
            startDestination = startDestination
        )
    }
}
