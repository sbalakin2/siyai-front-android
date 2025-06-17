package com.example.siyai_front_android.presentation.my_state

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyStateScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit
) {
    EmptyCyclesScreen(
        onContinueClick = onContinueClick
    )
}

@Preview
@Composable
private fun MyStateScreenPreview() {
    MyStateScreen {}
}