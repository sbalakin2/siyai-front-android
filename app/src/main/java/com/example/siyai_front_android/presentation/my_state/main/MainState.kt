package com.example.siyai_front_android.presentation.my_state.main

sealed interface MainState {
    data object Main : MainState
    data object Onboarding : MainState
    data object Loading : MainState
}