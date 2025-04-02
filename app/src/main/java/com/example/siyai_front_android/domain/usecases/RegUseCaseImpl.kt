package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.RegRepository
import com.example.siyai_front_android.presentation.reg.RegState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class RegUseCaseImpl @Inject constructor(
    private val regRepository: RegRepository
): RegUseCase {
    override suspend fun invoke(email: String, password: String): RegState {
        return when (val result = regRepository.registerUser(email, password)) {
            is NetworkResult.Success -> RegState.Success
            is NetworkResult.Error -> RegState.Error(result.code, result.message)
            is NetworkResult.Exception -> RegState.Exception(result.throwable.message.orEmpty())
        }
    }
}
