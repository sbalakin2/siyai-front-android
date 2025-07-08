package com.example.siyai_front_android.presentation.my_state.calendar_with_info

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.presentation.my_state.your_status_day.YourStatusDayScreen
import com.example.siyai_front_android.ui.components.calendar.CalendarGrid
import com.example.siyai_front_android.ui.components.calendar.RowDays
import com.example.siyai_front_android.utils.MonthData
import com.example.siyai_front_android.utils.createMonthData
import com.example.siyai_front_android.utils.getDateToRangeMap
import com.example.siyai_front_android.utils.getDaysStates


@Composable
fun CalendarWithInfoScreen(
    modifier: Modifier = Modifier,
    viewmodelFactory: ViewModelProvider.Factory,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: CalendarWithInfoViewModel = viewModel(factory = viewmodelFactory)
) {
    val cycles = viewModel.cycles.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        var showBottomSheet by remember { mutableStateOf(true) }

        if (showBottomSheet) {
            YourStatusDayScreen(
                onSelectedStatus = { status, note ->

                },
                onDismiss = { showBottomSheet = false }
            )
        }
        CalendarWithInfoContent(
            cycles = cycles.value,
            onEditClick = onEditClick,
            onBackClick = onBackClick
        )
    }
}

@Composable
private fun CalendarWithInfoContent(
    modifier: Modifier = Modifier,
    cycles: List<Cycle>,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            CalendarWithInfoHeader(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                onEditClick = onEditClick,
                onBackClick = onBackClick
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 112.dp)
            ) {
                SimpleDatePicker(cycles = cycles)
                DayInfo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
        CyclesButton(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            isCycleStarted = false,
            isCycleEnds = true,
            onClick = {}
        )
    }
}

@Composable
private fun CalendarWithInfoHeader(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val isClickedButton = remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { isClickedButton.handleSingleClick { onBackClick() } }
            ) {
                Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = stringResource(R.string.calendar_cycles),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            IconButton(
                onClick = { isClickedButton.handleSingleClick { onEditClick() } }
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        RowDays(modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp))
    }
}

@Composable
private fun SimpleDatePicker(
    modifier: Modifier = Modifier,
    cycles: List<Cycle>
) {
    Column(
        modifier = modifier.padding(top = 32.dp)
    ) {
        val context = LocalContext.current
        var monthOffset by remember { mutableIntStateOf(0) }

        val monthsData = remember(monthOffset) { getMonthData(context, monthOffset) }
        val dateToRangeMap = remember(cycles) { getDateToRangeMap(cycles) }

        val daysState = remember(monthsData) {
            getDaysStates(monthsData, dateToRangeMap, null)
        }

        HeaderSimpleDatePicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            date = monthsData.monthName,
            onClickNext = { monthOffset++ },
            onClickPrev = { monthOffset-- }
        )
        CalendarGrid(
            monthData = monthsData,
            daysStates = daysState,
            isCellsEnabled = false
        )
    }
}

@Composable
private fun HeaderSimpleDatePicker(
    modifier: Modifier = Modifier,
    date: String,
    onClickNext: () -> Unit,
    onClickPrev: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Row {
            val iconModifier = remember { Modifier
                .size(34.dp)
                .clip(CircleShape) }
            Icon(
                modifier = iconModifier.clickable { onClickPrev() },
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = iconModifier.clickable { onClickNext() },
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
private fun DayInfo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.about_day),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        RowDayInfo(
            icon = Icons.Default.DateRange,
            label = stringResource(R.string.day_cycle),
            value = "1"
        )
        RowDayInfo(
            icon = Icons.Default.Info,
            label = stringResource(R.string.phase_cycle),
            value = "Менструация"
        )
        RowDayInfo(
            icon = Icons.Default.PlayArrow,
            label = stringResource(R.string.start_cycle),
            value = "25.06.2025"
        )
    }
}

@Composable
private fun RowDayInfo(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun CyclesButton(
    modifier: Modifier = Modifier,
    isCycleStarted: Boolean,
    isCycleEnds: Boolean,
    onClick: () -> Unit
) {
    val (backgroundColor, textColor) = if (isCycleStarted) {
        MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.outline
    } else {
        MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.onPrimary
    }

    val (textButton, icon) = if (isCycleStarted) {
        stringResource(R.string.edit_cycles) to Icons.Default.Edit
    } else if (isCycleEnds) {
        stringResource(R.string.cycle_ended) to Icons.Default.Clear
    } else {
        stringResource(R.string.cycle_started) to Icons.Default.Check
    }

    Button(
        modifier = modifier.height(56.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = icon,
                tint = textColor,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = textButton,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = textColor
            )
        }
    }
}

private fun getMonthData(context: Context, monthOffset: Int = 0): MonthData {
    return createMonthData(context, monthOffset)
}

private inline fun MutableState<Boolean>.handleSingleClick(onClick: () -> Unit) {
    if (!this.value) {
        onClick()
        this.value = true
    }
}

@Preview
@Composable
private fun PreviewCalendarWitInfoScreen() {
    CalendarWithInfoScreen(
        viewmodelFactory = ViewModelProvider.NewInstanceFactory(),
        onEditClick = {},
        onBackClick = {}
    )
}