package com.example.siyai_front_android.presentation.my_state.calendar_with_info

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.siyai_front_android.R

enum class CyclePhase {
    MENSTRUAL,
    FOLLICULAR,
    OVULATION,
    LUTEAL;

    @Composable
    fun getPhase(): String = when (this) {
        MENSTRUAL -> stringResource(R.string.menstrual)
        FOLLICULAR -> stringResource(R.string.follicular)
        OVULATION -> stringResource(R.string.ovulation)
        LUTEAL -> stringResource(R.string.luteal)
    }
}