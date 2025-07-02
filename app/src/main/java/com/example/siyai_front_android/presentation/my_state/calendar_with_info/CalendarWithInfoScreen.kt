package com.example.siyai_front_android.presentation.my_state.calendar_with_info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.siyai_front_android.presentation.my_state.your_status_day.YourStatusDayScreen

@Composable
fun CalendarWithInfoScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        var showBottomSheet by remember { mutableStateOf(true) }

        if (showBottomSheet) {
            YourStatusDayScreen(
                onSelectedStatus = { status, note ->

                },
                onDismiss = { showBottomSheet = false }
            )
        }
    }
}