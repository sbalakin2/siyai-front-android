package com.example.siyai_front_android.presentation.my_state.select_cycles

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.siyai_front_android.R

enum class CyclesValidationError {
    SINGLE_DAY_NOT_ALLOWED,
    BACKWARD_SELECTION,
    RANGES_OVERLAP,
    MAX_RANGES_REACHED;

    @Composable
    fun getErrorMessage(): String = when (this) {
        SINGLE_DAY_NOT_ALLOWED -> stringResource(R.string.error_single_day)
        BACKWARD_SELECTION -> stringResource(R.string.error_backward)
        RANGES_OVERLAP -> stringResource(R.string.error_overlap)
        MAX_RANGES_REACHED -> stringResource(R.string.error_max_reached)
    }
}