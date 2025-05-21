package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthStatusUseCaseImpl @Inject constructor(
    private val repository: AuthStatusRepository
) : GetAuthStatusUseCase {
    override suspend fun invoke(): Flow<Boolean> = repository.isUserAuthorized()
}