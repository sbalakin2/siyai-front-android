package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import com.example.siyai_front_android.domain.repositories.ProfileStorageRepository
import javax.inject.Inject

class ExitFromAppUseCaseImpl @Inject constructor(
    private val authStatusRepository: AuthStatusRepository,
    private val profileStorageRepository: ProfileStorageRepository
) : ExitFromAppUseCase {
    override suspend fun invoke() {
        authStatusRepository.logOut()
        profileStorageRepository.clear()
    }
}