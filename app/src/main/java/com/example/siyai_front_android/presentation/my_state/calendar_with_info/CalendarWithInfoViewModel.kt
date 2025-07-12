package com.example.siyai_front_android.presentation.my_state.calendar_with_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.usecases.GetDailyStateUseCase
import com.example.siyai_front_android.domain.usecases.GetMyStateUseCase
import com.example.siyai_front_android.domain.usecases.MyStateChangeCyclesUseCase
import com.example.siyai_front_android.domain.usecases.SaveDailyStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class CalendarWithInfoViewModel @Inject constructor(
    private val getCyclesUseCase: GetMyStateUseCase,
    private val changeCyclesUseCase: MyStateChangeCyclesUseCase,
    private val saveDailyStateUseCase: SaveDailyStateUseCase,
    private val getDailyStateUseCase: GetDailyStateUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow<CalendarWithInfoState>(CalendarWithInfoState.Loading)
    val uiState: StateFlow<CalendarWithInfoState> = _uiState.asStateFlow()

    private val dateMillis = System.currentTimeMillis()

    init {
        processCommand(CalendarWithInfoCommand.LoadData)
    }

    fun processCommand(command: CalendarWithInfoCommand) {
        viewModelScope.launch {
            when (command) {
                is CalendarWithInfoCommand.SaveDailyState -> {
                    handleSaveDailyState(command.state, command.note)
                }
                CalendarWithInfoCommand.SaveCurrentCycle -> {
                    handleSaveCurrentCycle()
                }
                CalendarWithInfoCommand.EndCurrentPeriod -> {
                    handleEndCurrentPeriod()
                }
                CalendarWithInfoCommand.DismissBottomSheet -> {
                    handleDismissBottomSheet()
                }
                CalendarWithInfoCommand.LoadData -> {
                    handleLoadData()
                }
            }
        }
    }

    private suspend fun handleLoadData() {
        val data = getCyclesUseCase()
        _uiState.update {
            data?.let { myState ->
                val updatedCycles = checkAndUpdateActivePeriod(myState.cycles)
                val currentCycleInfo = calculateCycleInfo(updatedCycles)
                val hasStateForToday = getDailyStateUseCase() == null

                if (updatedCycles != myState.cycles) {
                    changeCyclesUseCase(updatedCycles)
                }

                CalendarWithInfoState.CycleInfo(
                    cycles = updatedCycles,
                    cycleDay = currentCycleInfo.cycleDay,
                    phase = currentCycleInfo.phase,
                    nextPeriodStart = currentCycleInfo.nextPeriodStart,
                    isOnPeriod = currentCycleInfo.isOnPeriod,
                    isShowDailyState = hasStateForToday,
                    canEndPeriod = calculateCanEndPeriod(updatedCycles),
                    wasEndedToday = calculateWasEndedToday(updatedCycles),
                )
            } ?: CalendarWithInfoState.Loading
        }
    }

    private suspend fun handleSaveDailyState(state: Int, note: String) {
        saveDailyStateUseCase(state, note)
    }

    private suspend fun handleSaveCurrentCycle() {
        val todayStart = atStartOfDay(System.currentTimeMillis())
        val currentState = (_uiState.value as? CalendarWithInfoState.CycleInfo)
            ?: return
        val averagePeriodLength = calculateAveragePeriodLength(currentState.cycles)
        val estimatedPeriodEnd = todayStart + (averagePeriodLength * 24 * 60 * 60 * 1000L)
        val newCycle = Cycle(
            start = todayStart,
            end = estimatedPeriodEnd,
            isOnPeriod = true
        )
        val updatedCycles = currentState.cycles + newCycle

        changeCyclesUseCase(updatedCycles)

        val newCycleInfo = calculateCycleInfo(updatedCycles)

        _uiState.update {
            newCycleInfo.copy(
                canEndPeriod = calculateCanEndPeriod(newCycleInfo.cycles),
                wasEndedToday = calculateWasEndedToday(newCycleInfo.cycles)
            )
        }
    }

    private suspend fun handleEndCurrentPeriod() {
        val currentState = (_uiState.value as? CalendarWithInfoState.CycleInfo)
            ?: return
        val activePeriod = currentState.cycles.find { it.isOnPeriod } ?: return
        val todayStart = atStartOfDay(System.currentTimeMillis())
        val updatedActivePeriod = activePeriod.copy(
            end = todayStart,
            isOnPeriod = false
        )

        val updatedCycles = currentState.cycles.map { cycle ->
            if (cycle.start == activePeriod.start) {
                updatedActivePeriod
            } else {
                cycle
            }
        }

        changeCyclesUseCase(updatedCycles)

        val newCycleInfo = calculateCycleInfo(updatedCycles)

        _uiState.update {
            newCycleInfo.copy(
                canEndPeriod = calculateCanEndPeriod(newCycleInfo.cycles),
                wasEndedToday = calculateWasEndedToday(newCycleInfo.cycles)
            )
        }
    }

    private fun handleDismissBottomSheet() {
        _uiState.update {
            if (it is CalendarWithInfoState.CycleInfo) {
                it.copy(isShowDailyState = false)
            } else {
                it
            }
        }
    }

    private fun checkAndUpdateActivePeriod(cycles: List<Cycle>): List<Cycle> {
        val activePeriod = cycles.find { it.isOnPeriod } ?: return cycles

        val todayStart = atStartOfDay(System.currentTimeMillis())
        val activePeriodEnd = atStartOfDay(activePeriod.end)

        return if (todayStart > activePeriodEnd) {
            cycles.map { cycle ->
                if (cycle.start == activePeriod.start && cycle.isOnPeriod) {
                    cycle.copy(end = todayStart)
                } else {
                    cycle
                }
            }
        } else {
            cycles
        }
    }

    private fun calculateAveragePeriodLength(cycles: List<Cycle>): Int {
        if (cycles.isEmpty()) return 5

        val periodLengths = cycles.mapNotNull { cycle ->
            if (cycle.end > cycle.start) {
                calculateDaysBetween(cycle.start, cycle.end)
            } else null
        }

        return if (periodLengths.isNotEmpty()) {
            periodLengths.takeLast(6).average().toInt().coerceIn(3, 8)
        } else {
            5
        }
    }

    private fun calculateCycleInfo(cycles: List<Cycle>): CalendarWithInfoState.CycleInfo {
        val sortedCycles = cycles.sortedBy { it.start }
        val averageCycleLength = calculateAverageCycleLength(cycles)

        val activePeriod = sortedCycles.find { it.isOnPeriod }

        if (activePeriod != null) {
            val cycleDay = calculateDaysBetween(activePeriod.start, dateMillis) + 1

            return CalendarWithInfoState.CycleInfo(
                cycles = cycles,
                cycleDay = cycleDay,
                phase = CyclePhase.MENSTRUAL,
                nextPeriodStart = calculateNextPeriodStart(activePeriod.start, averageCycleLength),
                isOnPeriod = true,
                canEndPeriod = calculateCanEndPeriod(cycles),
                wasEndedToday = calculateWasEndedToday(cycles)
            )
        } else {
            val lastPeriod = sortedCycles.filter { cycle ->
                cycle.start <= dateMillis
            }.maxByOrNull { it.start }

            if (lastPeriod != null) {
                val cycleDay = calculateDaysBetween(lastPeriod.start, dateMillis) + 1
                val phase = calculatePhase(cycleDay, averageCycleLength)

                return CalendarWithInfoState.CycleInfo(
                    cycles = cycles,
                    cycleDay = cycleDay,
                    phase = phase,
                    nextPeriodStart = calculateNextPeriodStart(lastPeriod.start, averageCycleLength),
                    isOnPeriod = false,
                    canEndPeriod = calculateCanEndPeriod(cycles),
                    wasEndedToday = calculateWasEndedToday(cycles)
                )
            } else {
                return CalendarWithInfoState.CycleInfo(
                    cycles = cycles,
                    cycleDay = 1,
                    phase = CyclePhase.FOLLICULAR,
                    nextPeriodStart = dateMillis + (28 * 24 * 60 * 60 * 1000L),
                    isOnPeriod = false,
                    canEndPeriod = false,
                    wasEndedToday = false
                )
            }
        }
    }

    private fun calculateAverageCycleLength(cycles: List<Cycle>): Int {
        if (cycles.size < 2) return 28
        val sortedCycles = cycles.sortedBy { it.start }
        val cycleLengths = mutableListOf<Int>()

        for (i in 1 until sortedCycles.size) {
            val length = calculateDaysBetween(sortedCycles[i-1].start, sortedCycles[i].start)
            cycleLengths.add(length)
        }

        return cycleLengths.takeLast(6).average().toInt()
    }

    private fun calculatePhase(cycleDay: Int, cycleLength: Int): CyclePhase {
        val ovulationDay = cycleLength - 14

        return when {
            cycleDay <= 4 -> CyclePhase.MENSTRUAL
            cycleDay < ovulationDay - 1 -> CyclePhase.FOLLICULAR
            cycleDay in (ovulationDay - 1)..(ovulationDay + 1) -> CyclePhase.OVULATION
            else -> CyclePhase.LUTEAL
        }
    }

    private fun calculateNextPeriodStart(periodStartMillis: Long, cycleLength: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = periodStartMillis
        calendar.add(Calendar.DAY_OF_YEAR, cycleLength)
        return calendar.timeInMillis
    }

    private fun calculateCanEndPeriod(cycles: List<Cycle>): Boolean {
        val activePeriod = cycles.find { it.isOnPeriod } ?: return false
        val todayStart = atStartOfDay(System.currentTimeMillis())
        val estimatedEndStart = atStartOfDay(activePeriod.end)
        return todayStart >= estimatedEndStart
    }

    private fun calculateWasEndedToday(cycles: List<Cycle>): Boolean {
        val todayStart = atStartOfDay(System.currentTimeMillis())
        return cycles.any { cycle ->
            !cycle.isOnPeriod && atStartOfDay(cycle.end) == todayStart
        }
    }

    private fun calculateDaysBetween(startMillis: Long, endMillis: Long): Int {
        val startCalendar = Calendar.getInstance().apply {
            timeInMillis = startMillis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val endCalendar = Calendar.getInstance().apply {
            timeInMillis = endMillis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val diffInMillis = endCalendar.timeInMillis - startCalendar.timeInMillis
        return (diffInMillis / (24 * 60 * 60 * 1000)).toInt()
    }

    private fun atStartOfDay(millis: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
}