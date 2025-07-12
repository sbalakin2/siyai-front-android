package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Daily
import com.example.siyai_front_android.domain.repositories.MyStateRepository
import javax.inject.Inject

class GetDailyStateUseCaseImpl @Inject constructor(
    private val myStateRepository: MyStateRepository
) : GetDailyStateUseCase {

    override suspend fun invoke(): Daily? {
        return myStateRepository.getStateForDate()
    }
}