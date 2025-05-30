package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.PasswordResetRepository
import com.example.siyai_front_android.presentation.auth.password_reset.PasswordResetState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class ResetPasswordUseCaseImpl @Inject constructor(
    private val passwordResetRepository: PasswordResetRepository
) : ResetPasswordUseCase {
    override suspend fun invoke(token: String, newPassword: String): PasswordResetState {
        return when (val result = passwordResetRepository.resetPassword(token, newPassword)) {
            is NetworkResult.Success -> {
                PasswordResetState.Success
            }
            is NetworkResult.Error -> {
                PasswordResetState.Error(result.code, result.message)
            }
            is NetworkResult.Exception -> {
                PasswordResetState.Exception(result.throwable.message.orEmpty())
            }
        }
    }
}