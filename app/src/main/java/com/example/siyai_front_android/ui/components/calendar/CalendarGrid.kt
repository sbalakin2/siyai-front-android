package com.example.siyai_front_android.ui.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.utils.DayState
import com.example.siyai_front_android.utils.MonthData
import com.example.siyai_front_android.utils.RangePosition
import com.example.siyai_front_android.utils.getCellStyles

@Composable
fun CalendarGrid(
    monthData: MonthData,
    daysStates: List<DayState>,
    onDateClick: (Long) -> Unit = {},
    isCellsEnabled: Boolean = true
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
                                        onDateClick = onDateClick,
                                        isCellEnabled = isCellsEnabled
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
    onDateClick: (Long) -> Unit,
    isCellEnabled: Boolean
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
            .clickable(enabled = dayState.isAvailable && isCellEnabled) {
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
        DayDot(dayState)
    }
}

@Composable
private fun BoxScope.DayDot(dayState: DayState) {
    if (dayState.rangePosition != RangePosition.None || dayState.isTempStart) {
        val color = if (dayState.isCycleStarted && !dayState.isAvailable)
            Color.White.copy(alpha = 0.4f)
        else Color.White

        Box(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(4.dp)
                .background(color, CircleShape)
                .align(Alignment.BottomCenter)
        )
    }
}