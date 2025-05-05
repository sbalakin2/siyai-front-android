package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import javax.inject.Inject

class ExitFromAppUseCaseImpl @Inject constructor(
    private val authStatusRepository: AuthStatusRepository
) : ExitFromAppUseCase {
    override suspend fun invoke() {
        authStatusRepository.logOut()
    }
}