package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.MyStateRepository
import javax.inject.Inject

class SaveDailyStateUseCaseImpl @Inject constructor(
    private val myStateRepository: MyStateRepository
) : SaveDailyStateUseCase {

    override suspend fun invoke(state: Int?, note: String?) {
        myStateRepository.saveStateForToday(state, note)
    }
}