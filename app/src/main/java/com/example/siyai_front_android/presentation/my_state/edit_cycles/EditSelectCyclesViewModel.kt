package com.example.siyai_front_android.presentation.my_state.edit_cycles

import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.MyStateChangeCyclesUseCase
import com.example.siyai_front_android.domain.usecases.MyStateGetCyclesUseCase
import com.example.siyai_front_android.presentation.my_state.common_cycles.BaseSelectCyclesViewModel
import com.example.siyai_front_android.presentation.my_state.common_cycles.SelectCyclesCommand
import com.example.siyai_front_android.presentation.my_state.common_cycles.SelectCyclesState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditSelectCyclesViewModel @Inject constructor(
    private val getCyclesUseCase: MyStateGetCyclesUseCase,
    changeCyclesUseCase: MyStateChangeCyclesUseCase
) : BaseSelectCyclesViewModel(changeCyclesUseCase) {

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
                SelectCyclesCommand.LoadCycles -> {
                    _uiState.update { SelectCyclesState(cycles = getCyclesUseCase().value) }
                }
            }
        }
    }
}