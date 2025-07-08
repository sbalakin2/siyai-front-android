package com.example.siyai_front_android.presentation.my_state.edit_cycles

import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.MyStateChangeCyclesUseCase
import com.example.siyai_front_android.domain.usecases.MyStateGetCyclesUseCase
import com.example.siyai_front_android.presentation.my_state.common_cycles.BaseCyclesViewModel
import com.example.siyai_front_android.presentation.my_state.common_cycles.SelectCyclesCommand
import com.example.siyai_front_android.presentation.my_state.common_cycles.SelectCyclesState
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditCyclesViewModel @Inject constructor(
    private val getCyclesUseCase: MyStateGetCyclesUseCase,
    changeCyclesUseCase: MyStateChangeCyclesUseCase
) : BaseCyclesViewModel(changeCyclesUseCase) {

    val uiState = _uiState.asStateFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            _uiState.update { SelectCyclesState(cycles = getCyclesUseCase().value) }
        }
    }

    fun processCommand(command: SelectCyclesCommand) {
        viewModelScope.launch {
            when (command) {
                is SelectCyclesCommand.SelectDate -> {
                    handleDateSelection(command.date, command.maxRangesCycle)
                }
                is SelectCyclesCommand.ConfirmDelete -> confirmDelete()
                is SelectCyclesCommand.CancelDelete -> cancelDelete()
                SelectCyclesCommand.Save -> saveCycles()
                SelectCyclesCommand.Back -> navigateToBack()
            }
        }
    }
}