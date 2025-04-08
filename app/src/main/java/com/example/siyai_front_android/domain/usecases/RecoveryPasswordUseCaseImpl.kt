package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.RecoveryPasswordRepository
import com.example.siyai_front_android.presentation.password_recovery.RecoveryPasswordState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class RecoveryPasswordUseCaseImpl @Inject constructor(
    private val recoveryPasswordRepository: RecoveryPasswordRepository
): RecoveryPasswordUseCase {
    override suspend fun invoke(email: String): RecoveryPasswordState {
        return when (val result = recoveryPasswordRepository.recoveryPassword(email)) {
            is NetworkResult.Success -> RecoveryPasswordState.Success
            is NetworkResult.Error -> RecoveryPasswordState.Error(result.code, result.message)
            is NetworkResult.Exception -> RecoveryPasswordState.Exception(result.throwable.message.orEmpty())
        }
    }
}
