package com.example.siyai_front_android.presentation.my_state

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
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.GradientButton
import com.example.siyai_front_android.ui.components.calendar.DateRange
import com.example.siyai_front_android.ui.components.calendar.MultiRangeDatePicker
import com.example.siyai_front_android.ui.components.dialog.MyStateDialog
import kotlinx.coroutines.delay

@Composable
fun CalendarMyStateScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    CalendarMyStateContent(
        onBackClick = onBackClick,
        onContinueClick = onContinueClick
    )
}

@Composable
private fun CalendarMyStateContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    var selectedRanges by rememberSaveable { mutableStateOf(listOf<DateRange>()) }
    var warningMessage by remember { mutableStateOf<String?>(null) }
    val deleteDialogState = rememberSaveable {
        mutableStateOf<Pair<Boolean, DateRange?>>(false to null)
    }

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
                maxRangesCycle = 3,
                monthsCount = 5,
                selectedRanges = selectedRanges,
                onShowWarning = { msg -> warningMessage = msg },
                onSelectedRangesChange = { selectedRanges = it },
                onShowDialog = { deleteDialogState.value = true to it }
            )
            CalendarButton(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 36.dp, start = 16.dp, end = 16.dp),
                maxRangesCycle = 3,
                selectedRangesCount = selectedRanges.size,
                onContinueClick = onContinueClick
            )
        }
        WarningToast(warningMessage = warningMessage)

        if (deleteDialogState.value.first) {
            MyStateDialog(
                title = stringResource(R.string.delete_cycle),
                onConfirm = {
                    selectedRanges = selectedRanges.filter { it != deleteDialogState.value.second }
                    deleteDialogState.value = false to null
                },
                onCancel = { deleteDialogState.value = false to null }
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
    maxRangesCycle: Int,
    selectedRangesCount: Int,
    onContinueClick: () -> Unit
) {
    val remaining = maxRangesCycle - selectedRangesCount

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
        if (selectedRangesCount == maxRangesCycle) {
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
    CalendarMyStateScreen(
        onContinueClick = {},
        onBackClick = {}
    )
}