package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.UserProfileData
import com.example.siyai_front_android.domain.repositories.CreateProfileRepository
import com.example.siyai_front_android.presentation.auth.lets_meet.LetsMeetState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class CreateProfileUseCaseImpl @Inject constructor(
    private val createProfileRepository: CreateProfileRepository
) : CreateProfileUseCase {
    override suspend fun invoke(data: UserProfileData): LetsMeetState {
        return when (val result = createProfileRepository.createUserProfile(data)) {
            is NetworkResult.Success -> LetsMeetState.Success
            is NetworkResult.Error -> LetsMeetState.Error(result.code, result.message)
            is NetworkResult.Exception -> LetsMeetState.Exception(result.throwable.message.orEmpty())
        }
    }
}