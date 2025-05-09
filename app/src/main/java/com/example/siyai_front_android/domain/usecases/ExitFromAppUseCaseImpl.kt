package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import com.example.siyai_front_android.domain.repositories.UserProfileRepository
import javax.inject.Inject

class ExitFromAppUseCaseImpl @Inject constructor(
    private val authStatusRepository: AuthStatusRepository,
    private val userProfileRepository: UserProfileRepository
) : ExitFromAppUseCase {
    override suspend fun invoke() {
        authStatusRepository.logOut()
        userProfileRepository.clear()
    }
}