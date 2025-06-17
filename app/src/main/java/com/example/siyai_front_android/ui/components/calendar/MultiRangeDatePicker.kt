package com.example.siyai_front_android.ui.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun MultiRangeDatePicker(
    modifier: Modifier = Modifier,
    selectedRanges: List<DateRange>,
    maxRangesCycle: Int,
    monthsCount: Int,
    onAddDateRange: (DateRange) -> Unit,
    onClickSelectedRange: (Int) -> Unit,
    onShowWarning: (String) -> Unit,
) {
    Column(modifier = modifier) {
        var tempStartDate by remember { mutableStateOf<Long?>(null) }
        val monthsData = remember { getMonthsData(monthsCount) }

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
                            date = date,
                            selectedRanges = selectedRanges,
                            tempStartDate = tempStartDate,
                            maxRangesCycle = maxRangesCycle,
                            onAddDateRange = { onAddDateRange(it) },
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
    selectedRanges: List<DateRange>,
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

@Composable
private fun CalendarGrid(
    monthData: MonthData,
    daysStates: List<DayState>,
    onDateClick: (Long) -> Unit
) {
    val totalCells = monthData.startDayOfWeek + monthData.daysInMonth
    val weeks = (totalCells + 6) / 7

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val cellSize = maxWidth / 7

        Column {
            repeat(weeks) { weekIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(7) { dayOfWeek ->
                        val cellIndex = weekIndex * 7 + dayOfWeek
                        val cellModifier = Modifier.size(cellSize)

                        when {
                            cellIndex < monthData.startDayOfWeek -> {
                                Spacer(modifier = cellModifier)
                            }
                            else -> {
                                val dayIndex = cellIndex - monthData.startDayOfWeek
                                if (dayIndex < monthData.daysInMonth) {
                                    val dayState = daysStates[dayIndex]
                                    CalendarDay(
                                        modifier = cellModifier,
                                        dayState = dayState,
                                        dayOfWeek = dayOfWeek,
                                        daysInMonth = monthData.daysInMonth,
                                        onDateClick = onDateClick
                                    )
                                } else {
                                    Spacer(modifier = cellModifier)
                                }
                            }
                        }
                    }
                }
                if (weekIndex < weeks - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun CalendarDay(
    modifier: Modifier,
    dayState: DayState,
    dayOfWeek: Int,
    daysInMonth: Int,
    onDateClick: (Long) -> Unit
) {
    val (backgroundColor, shape, textColor) = remember(
        dayState, dayOfWeek, daysInMonth
    ) {
        getCellStyles(dayState, dayOfWeek, daysInMonth)
    }

    val finalModifier = remember(modifier, dayState.isToday) {
        if (dayState.isToday)
            modifier.border(width = 1.dp, color = Color.White, shape = CircleShape)
        else
            modifier
    }

    Box(
        modifier = finalModifier
            .background(backgroundColor, shape)
            .clip(CircleShape)
            .clickable(enabled = dayState.isAvailable) {
                onDateClick(dayState.dateMillis)
            },
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = dayState.day.toString(),
            color = textColor,
            style = MaterialTheme.typography.bodyLarge
        )

        if ((dayState.rangePosition != RangePosition.None || dayState.isTempStart) && dayState.isAvailable) {
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(4.dp)
                    .background(Color.White, CircleShape)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
private fun RowDays(
    modifier: Modifier = Modifier
) {
    val dayHeaders = remember { listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС") }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        dayHeaders.forEach { day ->
            Text(
                text = day,
                color = Color.White.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}

private fun handleDateClick(
    date: Long,
    selectedRanges: List<DateRange>,
    tempStartDate: Long?,
    maxRangesCycle: Int
): DateClickResult {
    val existingRangeIndex = selectedRanges.indexOfFirst { range ->
        date >= range.start && date <= range.end
    }

    if (existingRangeIndex != -1)
        return DateClickResult.ShowDialog(existingRangeIndex)

    if (selectedRanges.size >= maxRangesCycle)
        return DateClickResult.Error("${ValidationError.MAX_RANGES_REACHED.message}: $maxRangesCycle")

    if (tempStartDate == null)
        return DateClickResult.Success

    if (date < tempStartDate)
        return DateClickResult.Error(ValidationError.BACKWARD_SELECTION.message)

    val start = minOf(tempStartDate, date)
    val end = maxOf(tempStartDate, date)
    val validationError = validateDateSelection(start, end, maxRangesCycle, selectedRanges)

    if (validationError != null)
        return DateClickResult.Error(validationError)

    return DateClickResult.Success
}

private fun processHandleClick(
    date: Long,
    selectedRanges: List<DateRange>,
    tempStartDate: Long?,
    maxRangesCycle: Int,
    onAddDateRange: (DateRange) -> Unit,
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
                val newRange = DateRange(
                    start = minOf(tempStartDate, date),
                    end = maxOf(tempStartDate, date)
                )

                onAddDateRange(newRange)
                return null
            }
        }
        is DateClickResult.Error -> {
            onShowWarning(result.message)
            return null
        }
        is DateClickResult.ShowDialog -> {
            onClickSelectedRange(result.index)
            return null
        }
    }
}

private fun getCellStyles(
    dayState: DayState,
    dayOfWeek: Int,
    daysInMonth: Int
): Triple<Color, Shape, Color> {
    val backgroundColor = when {
        !dayState.isAvailable -> Color.Transparent
        dayState.isTempStart || dayState.rangePosition != RangePosition.None -> Color(0xFFE54E7C)
        else -> Color.Transparent
    }

    val shape = when (dayState.rangePosition) {
        RangePosition.Start -> {
            if (dayOfWeek == 6 || dayState.day == daysInMonth) CircleShape
            else RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp)
        }
        RangePosition.End -> {
            if (dayOfWeek == 0 || dayState.day == 1) CircleShape
            else RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp)
        }
        RangePosition.Middle -> {
            when (dayOfWeek) {
                0 -> {
                    if (dayState.day == daysInMonth) CircleShape else RoundedCornerShape(
                        topStart = 50.dp,
                        bottomStart = 50.dp
                    )
                }
                6 -> {
                    if (dayState.day == 1) CircleShape
                    else RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp)
                }
                else -> {
                    when (dayState.day) {
                        1 -> RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp)
                        daysInMonth -> RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp)
                        else -> RectangleShape
                    }
                }
            }
        }
        else -> {
            RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp)
        }
    }

    val textColor = when {
        !dayState.isAvailable -> Color.White.copy(alpha = 0.3f)
        dayState.rangePosition != RangePosition.None || dayState.isTempStart -> Color.White
        else -> Color.White.copy(alpha = 0.8f)
    }

    return Triple(backgroundColor, shape, textColor)
}

private fun getDaysStates(
    monthData: MonthData,
    dateToRangeMap: Map<Long, Pair<Int, DateRange>>,
    tempStartDate: Long?
): List<DayState> {
    val today = getTodayTimeInMillis()

    return (1..monthData.daysInMonth).map { day ->
        val dateMillis = monthData.firstDayMillis + (day - 1) * 24 * 60 * 60 * 1000L
        val rangeInfo = dateToRangeMap[dateMillis]
        val isInRange = rangeInfo != null
        val isRangeStart = rangeInfo?.second?.start == dateMillis
        val isRangeEnd = rangeInfo?.second?.end == dateMillis
        val isTempStart = tempStartDate == dateMillis
        val isToday = dateMillis == today

        val rangePosition = when {
            isRangeStart -> RangePosition.Start
            isRangeEnd -> RangePosition.End
            isInRange -> RangePosition.Middle
            else -> RangePosition.None
        }

        DayState(
            day = day,
            dateMillis = dateMillis,
            isAvailable = dateMillis <= today,
            isToday = isToday,
            rangePosition = rangePosition,
            isTempStart = isTempStart,
            isInSelectedRange = isInRange,
            rangeIndex = rangeInfo?.first ?: -1
        )
    }
}

private fun getDateToRangeMap(selectedRanges: List<DateRange>): Map<Long, Pair<Int, DateRange>> =
    buildMap {
        selectedRanges.forEachIndexed { index, range ->
            var current = range.start
            while (current <= range.end) {
                put(current, index to range)
                current += 24 * 60 * 60 * 1000
            }
        }
    }

private fun getMonthsData(monthsCount: Int): List<MonthData> {
    val currentMonth = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val dateFormatter = SimpleDateFormat("LLLL yyyy г.", Locale("ru"))
    val currentCalendar = Calendar.getInstance()

    return (0..monthsCount).map { index ->
        val monthOffset = -index
        val monthCalendar = Calendar.getInstance().apply {
            timeInMillis = currentMonth.timeInMillis
            add(Calendar.MONTH, monthOffset)
        }

        val isCurrentMonth =
            monthCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
                    monthCalendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)

        MonthData(
            year = monthCalendar.get(Calendar.YEAR),
            month = monthCalendar.get(Calendar.MONTH),
            monthName = dateFormatter.format(monthCalendar.time),
            daysInMonth = monthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH),
            startDayOfWeek = (monthCalendar.get(Calendar.DAY_OF_WEEK) - 2 + 7) % 7,
            isCurrentMonth = isCurrentMonth,
            firstDayMillis = monthCalendar.timeInMillis
        )
    }
}

private fun getTodayTimeInMillis(): Long =
    Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

private fun validateDateSelection(
    start: Long,
    end: Long,
    maxRangesCycle: Int,
    selectedRanges: List<DateRange>
): String? {
    if (start == end) return ValidationError.SINGLE_DAY_NOT_ALLOWED.message

    if (selectedRanges.size >= maxRangesCycle)
        return "${ValidationError.MAX_RANGES_REACHED.message}: $maxRangesCycle"

    val newRange = DateRange(start, end)

    selectedRanges.forEach { existingRange ->
        if (!(newRange.end < existingRange.start || newRange.start > existingRange.end))
            return ValidationError.RANGES_OVERLAP.message

        if (newRange.start >= existingRange.start && newRange.end <= existingRange.end)
            return ValidationError.RANGE_INSIDE_ANOTHER.message

        if (existingRange.start >= newRange.start && existingRange.end <= newRange.end)
            return ValidationError.RANGE_INSIDE_ANOTHER.message
    }

    return null
}

data class DateRange(val start: Long, val end: Long)

data class MonthData(
    val year: Int,
    val month: Int,
    val monthName: String,
    val daysInMonth: Int,
    val startDayOfWeek: Int,
    val isCurrentMonth: Boolean,
    val firstDayMillis: Long
)

data class DayState(
    val day: Int,
    val dateMillis: Long,
    val isAvailable: Boolean,
    val isToday: Boolean,
    val rangePosition: RangePosition,
    val isTempStart: Boolean,
    val isInSelectedRange: Boolean = false,
    val rangeIndex: Int = -1
)

enum class RangePosition { None, Start, Middle, End }

enum class ValidationError(val message: String) {
    SINGLE_DAY_NOT_ALLOWED("Нельзя выбрать один день. Выберите диапазон дат"),
    BACKWARD_SELECTION("Нельзя выбирать даты раньше начальной даты диапазона"),
    RANGES_OVERLAP("Диапазоны дат не должны пересекаться"),
    RANGE_INSIDE_ANOTHER("Диапазоны не должны находиться один в другом"),
    MAX_RANGES_REACHED("Достигнуто максимальное количество диапазонов")
}

sealed class DateClickResult {
    data object Success : DateClickResult()
    data class Error(val message: String) : DateClickResult()
    data class ShowDialog(val index: Int) : DateClickResult()
}

@Preview
@Composable
private fun PreviewMultiRangeDatePicker() {
    MultiRangeDatePicker(
        selectedRanges = emptyList(),
        maxRangesCycle = 0,
        monthsCount = 3,
        onAddDateRange = {},
        onClickSelectedRange = {},
        onShowWarning = {}
    )
}