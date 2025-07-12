package com.example.siyai_front_android.presentation.my_state.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyStateScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit,
    onCalendarClick: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: MyStateViewModel = viewModel(factory = viewModelFactory)
) {
    val mainState by viewModel.mainState.collectAsStateWithLifecycle()

    when (mainState) {
        MainState.Loading -> {}
        MainState.Main -> MyStateContent(
            onContinueClick = onCalendarClick,
        )
        MainState.Onboarding -> OnboardingContent(onContinueClick = onContinueClick)
    }
}

@Preview
@Composable
private fun MyStateScreenPreview() {
    MyStateScreen(
        onContinueClick = {},
        onCalendarClick = {},
        viewModelFactory = ViewModelProvider.NewInstanceFactory()
    )
}