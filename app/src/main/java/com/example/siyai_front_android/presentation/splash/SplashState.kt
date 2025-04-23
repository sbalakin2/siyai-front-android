package com.example.siyai_front_android.presentation.splash

interface SplashState {
    object NavigateToAuth: SplashState
    object NavigateToHome: SplashState
    object Empty: SplashState
}