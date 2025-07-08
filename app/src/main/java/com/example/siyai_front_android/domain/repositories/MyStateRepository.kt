package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.Cycle
import kotlinx.coroutines.flow.StateFlow

interface MyStateRepository {

    val cycles: StateFlow<List<Cycle>>

    suspend fun changeCycles(cycles: List<Cycle>)
}