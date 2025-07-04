package com.example.siyai_front_android.presentation.my_state.calendar_with_info

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
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
    viewModel: CalendarWithInfoViewModel = viewModel(factory = viewmodelFactory)
) {
    val state = viewModel.cycles.collectAsState()
    val cycles = state.value

    Log.d("MyTag", cycles.toString())

    Box(modifier = modifier.fillMaxSize()) {
        var showBottomSheet by remember { mutableStateOf(true) }

        if (showBottomSheet) {
            YourStatusDayScreen(
                onSelectedStatus = { status, note ->

                },
                onDismiss = { showBottomSheet = false }
            )
        }
        CalendarWithInfoContent(cycles = cycles)
    }
}

@Composable
private fun CalendarWithInfoContent(
    cycles: List<Cycle>
) {
    Column {
        CalendarWithInfoHeader(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            onEditClick = {},
            onBackClick = {}
        )
        SimpleDatePicker(cycles = cycles)
    }
}

@Composable
private fun CalendarWithInfoHeader(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBackClick) {
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
            IconButton(onClick = onEditClick) {
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

        HeaderDatePicker(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
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
private fun HeaderDatePicker(
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
            val iconModifier = remember { Modifier.size(34.dp).clip(CircleShape) }
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

private fun getMonthData(context: Context, monthOffset: Int = 0): MonthData {
    return createMonthData(context, monthOffset)
}
