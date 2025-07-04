package com.example.siyai_front_android.presentation.my_state.calendar_with_info

import androidx.lifecycle.ViewModel
import com.example.siyai_front_android.domain.usecases.MyStateGetCyclesUseCase
import javax.inject.Inject

class CalendarWithInfoViewModel @Inject constructor(
    private val getCyclesUseCase: MyStateGetCyclesUseCase
) : ViewModel() {

    val cycles = getCyclesUseCase()
}