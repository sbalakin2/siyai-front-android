package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.VerificationRepository
import com.example.siyai_front_android.presentation.email_confirmation.VerificationState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class VerifyUseCaseImpl @Inject constructor(
    private val verificationRepository: VerificationRepository
) : VerifyUseCase {
    override suspend fun invoke(email: String): VerificationState {
        return when (val result = verificationRepository.verify(email)) {
            is NetworkResult.Success -> {
                VerificationState.Success(result.data.expDate, result.data.otp)
            }
            is NetworkResult.Error -> {
                VerificationState.Error(result.code, result.message)
            }
            is NetworkResult.Exception -> {
                VerificationState.Exception(result.throwable.message.orEmpty())
            }
        }
    }
}
