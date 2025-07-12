package com.example.siyai_front_android.presentation.my_state.select_cycles.edit_cycles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.presentation.my_state.select_cycles.SelectCyclesCommand
import com.example.siyai_front_android.presentation.my_state.select_cycles.SelectCyclesEvent
import com.example.siyai_front_android.ui.components.buttons.GradientButton
import com.example.siyai_front_android.ui.components.calendar.MultiRangeDatePicker
import com.example.siyai_front_android.ui.components.dialog.MyStateDialog
import com.example.siyai_front_android.ui.components.dialog.WarningSnackBar

private const val MAX_RANGES_CYCLES = 12

@Composable
fun EditCyclesScreen(
    modifier: Modifier = Modifier,
    onSaveCycles: () -> Unit,
    onBackClick: () -> Unit,
    onDeleteAllCycles: () -> Unit,
    viewmodelFactory: ViewModelProvider.Factory,
    viewModel: EditSelectCyclesViewModel = viewModel(factory = viewmodelFactory)
) {
    val state by viewModel.uiState.collectAsState()
    val event by viewModel.uiEvent.collectAsState(initial = null)

    LaunchedEffect(key1 = event) {
        if (event is SelectCyclesEvent.Back) onBackClick()
        if (event is SelectCyclesEvent.Continue) onSaveCycles()
        if (event is SelectCyclesEvent.Main) onDeleteAllCycles()
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.processCommand(SelectCyclesCommand.LoadCycles)
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            EditCyclesHeader(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
                onBackClick = { viewModel.processCommand(SelectCyclesCommand.Back) }
            )
            MultiRangeDatePicker(
                modifier = Modifier.weight(1f),
                selectedRanges = state.cycles,
                tempStartDate = state.tempStartDate,
                onDateClick = { date ->
                    viewModel.processCommand(
                        SelectCyclesCommand.SelectDate(date, MAX_RANGES_CYCLES)
                    )
                }
            )
            Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 50.dp)) {
                GradientButton(
                    modifier = Modifier.height(56.dp),
                    text = stringResource(R.string.save),
                    onClick = { viewModel.processCommand(SelectCyclesCommand.Save) }
                )
            }
        }

        if (event is SelectCyclesEvent.ValidateError) {
            val error = (event as SelectCyclesEvent.ValidateError).error
            WarningSnackBar(warningMessage = error.getErrorMessage())
        }

        if (state.isVisibleDialog) {
            MyStateDialog(
                title = stringResource(R.string.delete_cycle),
                onConfirm = { viewModel.processCommand(SelectCyclesCommand.ConfirmDelete) },
                onCancel = { viewModel.processCommand(SelectCyclesCommand.CancelDelete) }
            )
        }
    }
}

@Composable
private fun EditCyclesHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onBackClick
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Localized description",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = stringResource(R.string.calendar_cycles),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}