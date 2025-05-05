package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import javax.inject.Inject

class EnterToAppUseCaseImpl @Inject constructor(
    private val authStatusRepository: AuthStatusRepository
) : EnterToAppUseCase {
    override suspend fun invoke() {
        authStatusRepository.logIn()
    }
}