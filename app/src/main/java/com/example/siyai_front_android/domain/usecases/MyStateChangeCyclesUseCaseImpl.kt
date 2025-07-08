package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Cycle
import com.example.siyai_front_android.domain.repositories.MyStateRepository
import javax.inject.Inject

class MyStateChangeCyclesUseCaseImpl @Inject constructor(
    private val myStateRepository: MyStateRepository
) : MyStateChangeCyclesUseCase {

    override suspend operator fun invoke(cycles: List<Cycle>) {
        myStateRepository.changeCycles(cycles)
    }
}