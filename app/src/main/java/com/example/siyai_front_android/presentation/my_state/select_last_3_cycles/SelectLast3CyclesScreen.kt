package com.example.siyai_front_android.presentation.my_state.select_last_3_cycles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.presentation.my_state.common_cycles.SelectCyclesCommand
import com.example.siyai_front_android.presentation.my_state.common_cycles.SelectCyclesEvent
import com.example.siyai_front_android.ui.components.buttons.GradientButton
import com.example.siyai_front_android.ui.components.calendar.MultiRangeDatePicker
import com.example.siyai_front_android.ui.components.dialog.MyStateDialog
import com.example.siyai_front_android.ui.components.dialog.WarningSnackBar

private const val MAX_RANGES_CYCLE = 3
private const val MONTHS_COUNT = 6

@Composable
fun SelectLast3CyclesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    viewmodelFactory: ViewModelProvider.Factory,
    viewModel: SelectLast3CyclesViewModel = viewModel(factory = viewmodelFactory)
) {
    val state by viewModel.uiState.collectAsState()
    val event by viewModel.uiEvent.collectAsState(initial = null)

    LaunchedEffect(key1 = event) {
        if (event is SelectCyclesEvent.Back) onBackClick()
        if (event is SelectCyclesEvent.Continue) onContinueClick()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CalendarHeader(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp),
                onBackClick = { viewModel.processCommand(SelectCyclesCommand.Back) }
            )

            MultiRangeDatePicker(
                modifier = Modifier.weight(1f),
                selectedRanges = state.cycles,
                tempStartDate = state.tempStartDate,
                onDateClick = { date ->
                    viewModel.processCommand(
                        SelectCyclesCommand.SelectDate(date, MAX_RANGES_CYCLE)
                    )
                },
                monthsCount = MONTHS_COUNT
            )

            CalendarButton(
                modifier = Modifier.padding(top = 8.dp, bottom = 36.dp, start = 16.dp, end = 16.dp),
                selectedRangesCount = state.cycles.size,
                onContinueClick = {
                    viewModel.processCommand(SelectCyclesCommand.Save)
                }
            )
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
private fun CalendarHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Localized description",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            modifier = Modifier.padding(start = 24.dp),
            text = stringResource(R.string.state_calendar_title),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
private fun CalendarButton(
    modifier: Modifier = Modifier,
    selectedRangesCount: Int,
    onContinueClick: () -> Unit
) {
    val remaining = MAX_RANGES_CYCLE - selectedRangesCount

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedRangesCount in 1..< MAX_RANGES_CYCLE) {
            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onContinueClick() }
                    .padding(8.dp),
                text = stringResource(R.string.calendar_btn_skip),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(22.dp))
        }
        if (selectedRangesCount == MAX_RANGES_CYCLE) {
            GradientButton(
                modifier = Modifier.height(56.dp),
                text = stringResource(R.string.save),
                onClick = onContinueClick
            )
        } else {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                enabled = false
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.calendar_btn_next),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    val pluralText = pluralStringResource(R.plurals.cycles, remaining, remaining)
                    Text(
                        text = stringResource(R.string.сhoose_more_cycle, pluralText),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCalendarMyStateScreen() {
    SelectLast3CyclesScreen(
        onContinueClick = {},
        onBackClick = {},
        viewmodelFactory = ViewModelProvider.NewInstanceFactory()
    )
}