package com.example.siyai_front_android.presentation.my_state.select_last_3_cycles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.siyai_front_android.ui.components.buttons.GradientButton
import com.example.siyai_front_android.ui.components.calendar.MultiRangeDatePicker
import com.example.siyai_front_android.ui.components.dialog.MyStateDialog
import kotlinx.coroutines.delay

private const val MAX_RANGES_CYCLE = 3
private const val MONTH_COUNT = 5

@Composable
fun SelectLast3CyclesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
    viewmodelFactory: ViewModelProvider.Factory,
    viewModel: SelectLast3CyclesViewModel = viewModel(factory = viewmodelFactory)
) {
    val selectedRanges by viewModel.cycles.collectAsState(emptyList())
    var warningMessage by remember { mutableStateOf<String?>(null) }
    val deleteDialogState = rememberSaveable { mutableStateOf(false to -1) }

    LaunchedEffect(warningMessage) {
        warningMessage?.let {
            delay(3000)
            warningMessage = null
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CalendarHeader(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp),
                onBackClick = onBackClick
            )
            MultiRangeDatePicker(
                modifier = Modifier.weight(1f),
                maxRangesCycle = MAX_RANGES_CYCLE,
                monthsCount = MONTH_COUNT,
                selectedRanges = selectedRanges,
                onShowWarning = { msg -> warningMessage = msg },
                onAddDateRange = { start, end -> viewModel.addCycle(start, end) },
                onClickSelectedRange = { deleteDialogState.value = true to it }
            )
            CalendarButton(
                modifier = Modifier.padding(top = 8.dp, bottom = 36.dp, start = 16.dp, end = 16.dp),
                selectedRangesCount = selectedRanges.size,
                onContinueClick = onContinueClick
            )
        }
        WarningToast(warningMessage = warningMessage)

        if (deleteDialogState.value.first) {
            MyStateDialog(
                title = stringResource(R.string.delete_cycle),
                onConfirm = {
                    viewModel.deleteCycle(deleteDialogState.value.second)
                    deleteDialogState.value = false to -1
                },
                onCancel = { deleteDialogState.value = false to -1 }
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
        if (selectedRangesCount > 0) {
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
        if (selectedRangesCount == 3) {
            GradientButton(
                modifier = Modifier.height(56.dp),
                text = stringResource(R.string.save),
                onClick = onContinueClick
            )
        } else {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onContinueClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
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

@Composable
private fun BoxScope.WarningToast(
    warningMessage: String? = null
) {
    AnimatedVisibility(
        visible = warningMessage != null,
        modifier = Modifier
            .align(Alignment.BottomCenter),
        enter = slideInVertically(initialOffsetY = { it })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = warningMessage.orEmpty(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
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