package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.MyStateRepository
import javax.inject.Inject

class MyStateDeleteCycleUseCaseImpl @Inject constructor(
    private val myStateRepository: MyStateRepository
) : MyStateDeleteCycleUseCase {

    override suspend fun invoke(index: Int) {
        myStateRepository.removeCycle(index)
    }
}