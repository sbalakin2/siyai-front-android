package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.RegRepository
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class RegUseCaseImpl @Inject constructor(
    private val regRepository: RegRepository
): RegUseCase {
    override suspend fun invoke(email: String, password: String): NetworkResult<Unit> {
        return regRepository.registerUser(email, password)
    }
}
