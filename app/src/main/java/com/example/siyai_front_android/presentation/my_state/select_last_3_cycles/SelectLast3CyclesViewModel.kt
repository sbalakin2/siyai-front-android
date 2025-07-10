package com.example.siyai_front_android.presentation.my_state.select_last_3_cycles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.usecases.MyStateAddCycleUseCase
import com.example.siyai_front_android.domain.usecases.MyStateDeleteCycleUseCase
import com.example.siyai_front_android.domain.usecases.MyStateGetCyclesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectLast3CyclesViewModel @Inject constructor(
    private val getCyclesUseCase: MyStateGetCyclesUseCase,
    private val addCycleUseCase: MyStateAddCycleUseCase,
    private val removeCyclesUseCase: MyStateDeleteCycleUseCase
) : ViewModel() {

    private var lastAddTime = 0L
    private val addCycleThrottleMs = 200L

    val cycles = getCyclesUseCase()

    fun addCycle(startRange: Long, endRange: Long) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastAddTime < addCycleThrottleMs) return

        lastAddTime = currentTime

        viewModelScope.launch {
            addCycleUseCase(
                Cycle(start = startRange, end = endRange)
            )
        }
    }

    fun deleteCycle(id: Int) {
        viewModelScope.launch {
            removeCyclesUseCase(id)
        }
    }
}