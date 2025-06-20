package com.example.siyai_front_android.data.repositories

import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.repositories.MyStateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MyStateRepositoryImpl @Inject constructor() : MyStateRepository {

    private val _cycles = MutableStateFlow<List<Cycle>>(emptyList())
    override val cycles: StateFlow<List<Cycle>> = _cycles.asStateFlow()

    private var currentId = 0

    override suspend fun addCycle(cycle: Cycle) {
        val newCycle = cycle.copy(id = ++currentId)
        _cycles.update { it + newCycle }
    }

    override suspend fun removeCycle(id: Int) {
        _cycles.update { currentList ->
            currentList.filter { it.id != id }
        }
    }
}