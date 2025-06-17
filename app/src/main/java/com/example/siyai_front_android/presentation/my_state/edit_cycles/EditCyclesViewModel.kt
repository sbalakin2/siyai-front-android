package com.example.siyai_front_android.presentation.my_state.edit_cycles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.usecases.MyStateAddCycleUseCase
import com.example.siyai_front_android.domain.usecases.MyStateDeleteCycleUseCase
import com.example.siyai_front_android.domain.usecases.MyStateGetCyclesUseCase
import com.example.siyai_front_android.ui.components.calendar.DateRange
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditCyclesViewModel @Inject constructor(
    private val getCyclesUseCase: MyStateGetCyclesUseCase,
    private val addCycleUseCase: MyStateAddCycleUseCase,
    private val removeCyclesUseCase: MyStateDeleteCycleUseCase
) : ViewModel() {

    val cycles = getCyclesUseCase().map { list ->
        list.map { DateRange(it.start, it.end) }
    }

    fun addCycle(dateRange: DateRange) {
        viewModelScope.launch {
            addCycleUseCase(
                Cycle(dateRange.start, dateRange.end)
            )
        }
    }

    fun deleteCycle(index: Int) {
        viewModelScope.launch {
            removeCyclesUseCase(index)
        }
    }
}