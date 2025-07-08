package com.example.siyai_front_android.presentation.my_state.common_cycles

sealed interface SelectCyclesEvent {

    data object Continue : SelectCyclesEvent

    data object Back : SelectCyclesEvent

    data class ValidateError(val error: ValidationError) : SelectCyclesEvent
}