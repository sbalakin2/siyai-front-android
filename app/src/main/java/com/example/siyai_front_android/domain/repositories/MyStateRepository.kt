package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.Cycle
import kotlinx.coroutines.flow.StateFlow

interface MyStateRepository {

    val cycles: StateFlow<List<Cycle>>

    suspend fun addCycle(cycle: Cycle)

    suspend fun removeCycle(id: Int)
}