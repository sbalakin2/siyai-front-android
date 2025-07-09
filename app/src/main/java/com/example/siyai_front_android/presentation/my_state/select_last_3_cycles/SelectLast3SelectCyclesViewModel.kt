package com.example.siyai_front_android.presentation.my_state.select_last_3_cycles

import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.MyStateChangeCyclesUseCase
import com.example.siyai_front_android.presentation.my_state.common_cycles.BaseSelectCyclesViewModel
import com.example.siyai_front_android.presentation.my_state.common_cycles.SelectCyclesCommand
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectLast3SelectCyclesViewModel @Inject constructor(
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
                SelectCyclesCommand.LoadCycles -> {}
            }
        }
    }
}