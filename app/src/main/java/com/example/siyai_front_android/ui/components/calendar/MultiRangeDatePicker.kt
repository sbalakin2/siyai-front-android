package com.example.siyai_front_android.ui.components.calendar

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.utils.MonthData
import com.example.siyai_front_android.utils.createMonthData
import com.example.siyai_front_android.utils.getDateToRangeMap
import com.example.siyai_front_android.utils.getDaysStates

@Composable
fun MultiRangeDatePicker(
    modifier: Modifier = Modifier,
    selectedRanges: List<Cycle>,
    tempStartDate: Long?,
    onDateClick: (Long) -> Unit,
    monthsCount: Int = 12,
) {
    Column(modifier = modifier) {
        val context = LocalContext.current
        val monthsData = remember { getMonthsData(context, monthsCount) }

        RowDays(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(40.dp),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(
                items = monthsData,
                key = { "${it.year}-${it.month}" }
            ) { monthData ->
                MonthCalendar(
                    monthData = monthData,
                    selectedRanges = selectedRanges,
                    tempStartDate = tempStartDate,
                    onDateClick = onDateClick
                )
            }
        }
    }
}

@Composable
private fun MonthCalendar(
    modifier: Modifier = Modifier,
    monthData: MonthData,
    selectedRanges: List<Cycle>,
    tempStartDate: Long?,
    onDateClick: (Long) -> Unit
) {
    val dateToRangeMap = remember(selectedRanges) {
        getDateToRangeMap(selectedRanges)
    }

    val daysStates = remember(monthData, selectedRanges, tempStartDate) {
        getDaysStates(monthData, dateToRangeMap, tempStartDate)
    }

    Column(modifier = modifier) {
        Text(
            text = monthData.monthName,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = if (monthData.isCurrentMonth) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
        )
        CalendarGrid(
            monthData = monthData,
            daysStates = daysStates,
            onDateClick = onDateClick
        )
    }
}

private fun getMonthsData(context: Context, monthsCount: Int): List<MonthData> {
    return (0..monthsCount).map { index -> createMonthData(context, -index) }
}

@Preview
@Composable
private fun PreviewMultiRangeDatePicker() {
    MultiRangeDatePicker(
        selectedRanges = emptyList(),
        monthsCount = 3,
        tempStartDate = 0,
        onDateClick = {}
    )
}