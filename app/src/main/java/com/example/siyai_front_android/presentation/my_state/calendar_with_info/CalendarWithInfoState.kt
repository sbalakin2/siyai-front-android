package com.example.siyai_front_android.presentation.my_state.calendar_with_info

import com.example.siyai_front_android.domain.dto.Cycle

sealed interface CalendarWithInfoState {
    data class CycleInfo(
        val cycles: List<Cycle>,
        val cycleDay: Int,
        val phase: CyclePhase,
        val nextPeriodStart: Long,
        val isOnPeriod: Boolean,
        val isShowDailyState: Boolean = false,
        val canEndPeriod: Boolean = false,
        val wasEndedToday: Boolean = false,
    ) : CalendarWithInfoState

    data object Loading : CalendarWithInfoState
}