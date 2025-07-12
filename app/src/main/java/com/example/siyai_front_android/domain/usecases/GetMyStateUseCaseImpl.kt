package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.MyState
import com.example.siyai_front_android.domain.repositories.MyStateRepository
import javax.inject.Inject

class GetMyStateUseCaseImpl @Inject constructor(
    private val myStateRepository: MyStateRepository
) : GetMyStateUseCase {

    override suspend fun invoke(): MyState? = myStateRepository.getMyState()
}