package com.example.siyai_front_android.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    navigateToAuth: () -> Unit,
    navigateToHome: () -> Unit
){
    var state by remember { mutableStateOf<SplashState>(SplashState.Empty) }

    LaunchedEffect(state) {
        when(state){
            is SplashState.Empty -> {}
            is SplashState.NavigateToAuth -> navigateToAuth.invoke()
            is SplashState.NavigateToHome -> navigateToHome.invoke()
        }
    }

    // пока не готов функционал с токенами
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier.align(Alignment.Center)) {
            PrimaryButton(text = "auth", onClick = { state = SplashState.NavigateToAuth } )
            Spacer(modifier = Modifier.height(10.dp))
            PrimaryButton(text = "home", onClick = { state = SplashState.NavigateToHome } )
        }
    }
}