package com.example.siyai_front_android.presentation.my_state.your_status_day

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.siyai_front_android.R

enum class YourStatus(
    @StringRes val titleRes: Int,
    val color: Color
) {
    APATHY(R.string.status_apathy, Color(0xFF991B1B)),
    ANGER(R.string.status_anger, Color(0xFFEF4444)),
    SADNESS(R.string.status_sadness, Color(0xFFFF5555)),
    ANXIETY(R.string.status_anxiety, Color(0xFFD97706)),
    CALMNESS(R.string.status_calmness, Color(0xFFFBBF24)),
    JOY(R.string.status_joy, Color(0xFFFDE047)),
    HAPPINESS(R.string.status_happiness, Color(0xFF15803D)),
    GRATITUDE(R.string.status_gratitude, Color(0xFF22C55E)),
    LOVE(R.string.status_love, Color(0xFF099DAF));

    companion object {
        @Composable
        fun getStatusesList(): List<String> = entries.map {
            stringResource(id = it.titleRes)
        }

        @Composable
        fun getStatusColor(index: Int): Color = entries[index].color
    }
}