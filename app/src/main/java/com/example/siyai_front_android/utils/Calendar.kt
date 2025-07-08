package com.example.siyai_front_android.utils

import android.content.Context
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.domain.dto.Cycle
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getCellStyles(
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

fun getDaysStates(
    monthData: MonthData,
    dateToRangeMap: Map<Long, Pair<Int, Cycle>>?,
    tempStartDate: Long?
): List<DayState> {
    val today = getTodayTimeInMillis()

    return (1..monthData.daysInMonth).map { day ->
        val dateMillis = monthData.firstDayMillis + (day - 1) * 24 * 60 * 60 * 1000L
        val rangeInfo = dateToRangeMap?.get(dateMillis)
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

fun getDateToRangeMap(selectedRanges: List<Cycle>): Map<Long, Pair<Int, Cycle>> =
    buildMap {
        selectedRanges.forEachIndexed { index, range ->
            var current = range.start
            while (current <= range.end) {
                put(current, index to range)
                current += 24 * 60 * 60 * 1000
            }
        }
    }

fun getTodayTimeInMillis(): Long =
    Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

fun createMonthData(context: Context, monthOffset: Int): MonthData {
    val dateFormatter = createDateFormatter(context)
    val monthCalendar = createMonthCalendar(monthOffset)
    val isCurrentMonth = isCurrentMonth(monthCalendar)

    return MonthData(
        year = monthCalendar.get(Calendar.YEAR),
        month = monthCalendar.get(Calendar.MONTH),
        monthName = dateFormatter.format(monthCalendar.time),
        daysInMonth = monthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH),
        startDayOfWeek = (monthCalendar.get(Calendar.DAY_OF_WEEK) - 2 + 7) % 7,
        isCurrentMonth = isCurrentMonth,
        firstDayMillis = monthCalendar.timeInMillis
    )
}

private fun createMonthStartCalendar(): Calendar {
    return Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
}

private fun createMonthCalendar(monthOffset: Int): Calendar {
    val currentMonth = createMonthStartCalendar()
    return Calendar.getInstance().apply {
        timeInMillis = currentMonth.timeInMillis
        add(Calendar.MONTH, monthOffset)
    }
}

private fun createDateFormatter(context: Context): SimpleDateFormat {
    return SimpleDateFormat(
        context.getString(R.string.date_header_format),
        Locale(context.getString(R.string.locale_ru))
    )
}

private fun isCurrentMonth(calendar: Calendar): Boolean {
    val currentCalendar = Calendar.getInstance()
    return calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
            calendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)
}

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

data class MonthData(
    val year: Int,
    val month: Int,
    val monthName: String,
    val daysInMonth: Int,
    val startDayOfWeek: Int,
    val isCurrentMonth: Boolean,
    val firstDayMillis: Long
)

enum class RangePosition { None, Start, Middle, End }