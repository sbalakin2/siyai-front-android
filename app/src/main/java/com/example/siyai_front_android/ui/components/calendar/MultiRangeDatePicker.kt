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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.utils.MonthData
import com.example.siyai_front_android.utils.createMonthData
import com.example.siyai_front_android.utils.getDateToRangeMap
import com.example.siyai_front_android.utils.getDaysStates

@Composable
fun MultiRangeDatePicker(
    modifier: Modifier = Modifier,
    selectedRanges: List<Cycle>,
    maxRangesCycle: Int,
    monthsCount: Int,
    onAddDateRange: (Long, Long) -> Unit,
    onClickSelectedRange: (Int) -> Unit,
    onShowWarning: (String) -> Unit,
) {
    Column(modifier = modifier) {
        val context = LocalContext.current
        var tempStartDate by remember { mutableStateOf<Long?>(null) }
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
                    onDateClick = { date ->
                        tempStartDate = processHandleClick(
                            context = context,
                            date = date,
                            selectedRanges = selectedRanges,
                            tempStartDate = tempStartDate,
                            maxRangesCycle = maxRangesCycle,
                            onAddDateRange = { start, end -> onAddDateRange(start, end) },
                            onShowWarning = onShowWarning,
                            onClickSelectedRange = onClickSelectedRange
                        )
                    }
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

private fun handleDateClick(
    date: Long,
    selectedRanges: List<Cycle>,
    tempStartDate: Long?,
    maxRangesCycle: Int
): DateClickResult {
    val existingRange = selectedRanges.find { range ->
        date >= range.start && date <= range.end
    }

    if (existingRange != null)
        return DateClickResult.ShowDialog(existingRange.id)

    if (selectedRanges.size >= maxRangesCycle)
        return DateClickResult.Error(ValidationError.MAX_RANGES_REACHED)

    if (tempStartDate == null)
        return DateClickResult.Success

    if (date < tempStartDate)
        return DateClickResult.Error(ValidationError.BACKWARD_SELECTION)

    val start = minOf(tempStartDate, date)
    val end = maxOf(tempStartDate, date)
    val validationError = validateDateSelection(start, end, maxRangesCycle, selectedRanges)

    if (validationError != null)
        return DateClickResult.Error(validationError)

    return DateClickResult.Success
}

private fun processHandleClick(
    context: Context,
    date: Long,
    selectedRanges: List<Cycle>,
    tempStartDate: Long?,
    maxRangesCycle: Int,
    onAddDateRange: (Long, Long) -> Unit,
    onShowWarning: (String) -> Unit,
    onClickSelectedRange: (Int) -> Unit
): Long? {

    val result = handleDateClick(
        date = date,
        selectedRanges = selectedRanges,
        tempStartDate = tempStartDate,
        maxRangesCycle = maxRangesCycle
    )

    when (result) {
        is DateClickResult.Success -> {
            if (tempStartDate == null) {
                return date
            } else {
                onAddDateRange(
                    minOf(tempStartDate, date),
                    maxOf(tempStartDate, date)
                )
                return null
            }
        }
        is DateClickResult.Error -> {
            val message = result.validationError.getErrorMessage(context)
            onShowWarning(message)
            return null
        }
        is DateClickResult.ShowDialog -> {
            onClickSelectedRange(result.index)
            return null
        }
    }
}

private fun getMonthsData(context: Context, monthsCount: Int): List<MonthData> {
    return (0..monthsCount).map { index -> createMonthData(context, -index) }
}

private fun validateDateSelection(
    start: Long,
    end: Long,
    maxRangesCycle: Int,
    selectedRanges: List<Cycle>
): ValidationError? {
    if (start == end) return ValidationError.SINGLE_DAY_NOT_ALLOWED

    if (selectedRanges.size >= maxRangesCycle)
        return ValidationError.MAX_RANGES_REACHED

    selectedRanges.forEach { existingRange ->
        if (!(end < existingRange.start || start > existingRange.end))
            return ValidationError.RANGES_OVERLAP

        if (start >= existingRange.start && end <= existingRange.end)
            return ValidationError.RANGE_INSIDE_ANOTHER

        if (existingRange.start >= start && existingRange.end <= end)
            return ValidationError.RANGE_INSIDE_ANOTHER
    }

    return null
}

private fun ValidationError.getErrorMessage(context: Context): String = when (this) {
    ValidationError.SINGLE_DAY_NOT_ALLOWED -> context.getString(R.string.error_single_day)
    ValidationError.BACKWARD_SELECTION -> context.getString(R.string.error_backward)
    ValidationError.RANGES_OVERLAP -> context.getString(R.string.error_overlap)
    ValidationError.RANGE_INSIDE_ANOTHER -> context.getString(R.string.error_inside)
    ValidationError.MAX_RANGES_REACHED -> context.getString(R.string.error_max_reached)
}

private enum class ValidationError {
    SINGLE_DAY_NOT_ALLOWED,
    BACKWARD_SELECTION,
    RANGES_OVERLAP,
    RANGE_INSIDE_ANOTHER,
    MAX_RANGES_REACHED
}

private sealed class DateClickResult {
    data object Success : DateClickResult()
    data class Error(val validationError: ValidationError) : DateClickResult()
    data class ShowDialog(val index: Int) : DateClickResult()
}

@Preview
@Composable
private fun PreviewMultiRangeDatePicker() {
    MultiRangeDatePicker(
        selectedRanges = emptyList(),
        maxRangesCycle = 0,
        monthsCount = 3,
        onAddDateRange = { _, _ -> },
        onClickSelectedRange = {},
        onShowWarning = {}
    )
}