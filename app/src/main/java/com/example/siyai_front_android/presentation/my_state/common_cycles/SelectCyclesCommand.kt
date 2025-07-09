package com.example.siyai_front_android.presentation.my_state.common_cycles

sealed interface SelectCyclesCommand {

    data class SelectDate(
        val date: Long,
        val maxRangesCycle: Int
    ) : SelectCyclesCommand

    data object LoadCycles : SelectCyclesCommand

    data object ConfirmDelete : SelectCyclesCommand

    data object CancelDelete : SelectCyclesCommand

    data object Save : SelectCyclesCommand

    data object Back : SelectCyclesCommand
}