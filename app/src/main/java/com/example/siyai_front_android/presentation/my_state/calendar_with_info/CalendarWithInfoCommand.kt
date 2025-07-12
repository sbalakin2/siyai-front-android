package com.example.siyai_front_android.presentation.my_state.calendar_with_info

sealed interface CalendarWithInfoCommand {
    data class SaveDailyState(val state: Int, val note: String) : CalendarWithInfoCommand
    data object SaveCurrentCycle : CalendarWithInfoCommand
    data object EndCurrentPeriod : CalendarWithInfoCommand
    data object DismissBottomSheet : CalendarWithInfoCommand
    data object LoadData : CalendarWithInfoCommand
}