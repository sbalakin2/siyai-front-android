package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.dto.Daily
import com.example.siyai_front_android.domain.dto.MyState

interface MyStateRepository {

    suspend fun changeCycles(cycles: List<Cycle>)

    suspend fun getMyState(): MyState?

    suspend fun saveStateForToday(state: Int?, note: String?)

    suspend fun getStateForDate(): Daily?
}