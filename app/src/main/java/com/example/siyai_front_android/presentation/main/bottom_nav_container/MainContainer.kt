package com.example.siyai_front_android.presentation.main.bottom_nav_container

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.siyai_front_android.di.ViewModelFactory
import com.example.siyai_front_android.ui.components.bottom_nav.BottomNavBar
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun MainContainer(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory
) {
    val navController = rememberNavController()
    val navState by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedItem = navState?.destination?.route?.toMainRoute() ?: MainRoute.Home
            )
        }
    ) { innerPadding ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            viewModelFactory = viewModelFactory
        )
    }
}

@Composable
@Preview
private fun MainContainer_Preview(){
    SiyaifrontandroidTheme {
        MainContainer(viewModelFactory = ViewModelFactory(mapOf()))
    }
}