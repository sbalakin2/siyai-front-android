package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.repositories.MyStateRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MyStateGetCyclesUseCaseImpl @Inject constructor(
    private val myStateRepository: MyStateRepository
) : MyStateGetCyclesUseCase {

    override fun invoke(): StateFlow<List<Cycle>> = myStateRepository.cycles
}