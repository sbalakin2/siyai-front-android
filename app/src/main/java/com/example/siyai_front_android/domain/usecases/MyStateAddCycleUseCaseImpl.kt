package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.repositories.MyStateRepository
import javax.inject.Inject

class MyStateAddCycleUseCaseImpl @Inject constructor(
    private val myStateRepository: MyStateRepository
) : MyStateAddCycleUseCase {

    override suspend fun invoke(cycle: Cycle) {
        myStateRepository.addCycle(cycle)
    }
}