package com.example.siyai_front_android.data.mappers

import com.example.siyai_front_android.data.local.model.CycleDb
import com.example.siyai_front_android.data.local.model.DailyDb
import com.example.siyai_front_android.data.local.model.MyStateWithCycles
import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.dto.Daily
import com.example.siyai_front_android.domain.dto.MyState

fun Cycle.toCycleDb(myStateId: Int): CycleDb = CycleDb(
    id = id,
    myStateId = myStateId,
    start = start,
    end = end,
    isOnPeriod = isOnPeriod
)

fun List<Cycle>.toCycleDbList(myStateId: Int): List<CycleDb> = map {
    it.toCycleDb(myStateId)
}

fun List<CycleDb>.toCycleEntityList(): List<Cycle> = map {
    Cycle(
        id = it.id,
        start = it.start,
        end = it.end,
        isOnPeriod = it.isOnPeriod
    )
}

fun MyStateWithCycles.toMyStateEntity(): MyState = MyState(
    id = myState.id,
    userEmail = myState.userEmail,
    cycles = cycles.toCycleEntityList()
)

fun Daily.toDailyDb(myStateId: Int): DailyDb = DailyDb(
    date = date,
    myStateId = myStateId,
    state = state,
    note = note
)

fun DailyDb.toDailyEntity(): Daily = Daily(
    date = date,
    state = state,
    note = note
)