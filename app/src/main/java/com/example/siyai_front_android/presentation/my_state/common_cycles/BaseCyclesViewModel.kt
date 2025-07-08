package com.example.siyai_front_android.presentation.my_state.common_cycles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.usecases.MyStateChangeCyclesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseCyclesViewModel(
    protected val changeCyclesUseCase: MyStateChangeCyclesUseCase,
) : ViewModel() {

    protected val _uiState = MutableStateFlow(SelectCyclesState())
    protected val _uiEvent = MutableSharedFlow<SelectCyclesEvent?>()

    protected fun handleDateSelection(date: Long, maxRangesCycle: Int) {
        val currentState = _uiState.value
        val existingCycle = currentState.cycles.find { it.start <= date && it.end >= date }

        if (existingCycle != null) {
            showDeleteDialog(existingCycle.id)
        } else if (currentState.tempStartDate == null) {
            _uiState.update { currentState.copy(tempStartDate = date) }
        } else {
            createCycleFromRange(currentState.tempStartDate, date, maxRangesCycle, currentState.cycles)
        }
    }

    protected fun confirmDelete() {
        _uiState.update { currentState ->
            val updatedCycles = currentState.cycles.filter {
                it.id != currentState.deleteCycleId
            }
            currentState.copy(cycles = updatedCycles, isVisibleDialog = false)
        }
    }

    protected fun cancelDelete() {
        _uiState.update { currentState ->
            currentState.copy(isVisibleDialog = false, deleteCycleId = null)
        }
    }

    protected fun saveCycles() {
        viewModelScope.launch {
            val cycles = _uiState.value.cycles
            changeCyclesUseCase(cycles)
            _uiEvent.emit(SelectCyclesEvent.Continue)
        }
    }

    protected fun navigateToBack() {
        viewModelScope.launch {
            _uiEvent.emit(SelectCyclesEvent.Back)
        }
    }

    private fun createCycleFromRange(
        startDate: Long,
        endDate: Long,
        maxRangesCycle: Int,
        currentCycles: List<Cycle>
    ) {
        val currentId = if (currentCycles.isNotEmpty()) currentCycles.last().id + 1 else 0
        val newCycle = Cycle(
            id = currentId,
            start = startDate,
            end = endDate
        )

        val validationResult = validateDateRange(newCycle, maxRangesCycle, currentCycles)
        if (validationResult != null) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.copy(tempStartDate = null)
                }
                _uiEvent.emit(SelectCyclesEvent.ValidateError(validationResult))
                delay(3000)
                _uiEvent.emit(null)
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    cycles = currentCycles + newCycle,
                    tempStartDate = null
                )
            }
        }
    }

    private fun showDeleteDialog(cycleId: Int) {
        _uiState.update { currentState ->
            currentState.copy(isVisibleDialog = true, deleteCycleId = cycleId)
        }
    }

    private fun validateDateRange(
        newCycle: Cycle,
        maxRangesCycle: Int,
        currentCycles: List<Cycle>
    ): ValidationError? {
        return when {
            newCycle.start == newCycle.end -> ValidationError.SINGLE_DAY_NOT_ALLOWED
            newCycle.start > newCycle.end -> ValidationError.BACKWARD_SELECTION
            currentCycles.size >= maxRangesCycle -> ValidationError.MAX_RANGES_REACHED
            currentCycles.any { existing ->
                !(newCycle.end < existing.start || newCycle.start > existing.end)
            } -> ValidationError.RANGES_OVERLAP
            else -> null
        }
    }
}