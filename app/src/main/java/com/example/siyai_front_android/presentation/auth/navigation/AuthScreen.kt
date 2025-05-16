package com.example.siyai_front_android.presentation.auth.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.siyai_front_android.domain.dto.AuthProgress

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    enterToApp: (progress: AuthProgress) -> Unit
) {
    val navController = rememberNavController()

    AuthNavHost(
        navController = navController,
        modifier = modifier,
        viewModelFactory = viewModelFactory,
        enterToApp = enterToApp
    )
}