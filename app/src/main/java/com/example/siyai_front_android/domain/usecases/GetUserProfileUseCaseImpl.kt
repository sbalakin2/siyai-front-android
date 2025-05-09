package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.domain.repositories.UserProfileRepository
import javax.inject.Inject

class GetUserProfileUseCaseImpl @Inject constructor(
    private val repository: UserProfileRepository
) : GetUserProfileUseCase {
    override suspend fun invoke(): Result<Profile> = repository.getUserProfile()
}