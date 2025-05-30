package com.example.siyai_front_android.presentation.main.bottom_nav_container

import android.util.Log
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
    exitFromApp: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory,
) {
    val navController = rememberNavController()
    val navState by navController.currentBackStackEntryAsState()

    val screensWithBottomBar = listOf(
        MainRoute.Home,
        MainRoute.Training,
        MainRoute.Audio,
        MainRoute.Profile
    )

    val mainRoute = navState?.destination?.route?.toMainRoute()

    val isShowBottomBar = mainRoute in screensWithBottomBar

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (isShowBottomBar) {
                Log.d("MyTag", "MainContainer: $screensWithBottomBar")
                BottomNavBar(
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            viewModelFactory = viewModelFactory,
            exitFromApp = exitFromApp
        )
    }
}

@Composable
@Preview
private fun MainContainer_Preview(){
    SiyaifrontandroidTheme {
        MainContainer(viewModelFactory = ViewModelFactory(mapOf()), exitFromApp = {})
    }
}