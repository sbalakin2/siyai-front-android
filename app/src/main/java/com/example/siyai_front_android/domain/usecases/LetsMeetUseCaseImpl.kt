package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.model.UserProfileData
import com.example.siyai_front_android.domain.repositories.LetsMeetRepository
import com.example.siyai_front_android.presentation.welcome.LetsMeetState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class LetsMeetUseCaseImpl @Inject constructor(
    private val letsMeetRepository: LetsMeetRepository
) : LetsMeetUseCase {
    override suspend fun invoke(data: UserProfileData): LetsMeetState {
        return when (val result = letsMeetRepository.createUserProfile(data)) {
            is NetworkResult.Success -> LetsMeetState.Success
            is NetworkResult.Error -> LetsMeetState.Error(result.code, result.message)
            is NetworkResult.Exception -> LetsMeetState.Exception(result.throwable.message.orEmpty())
        }
    }
}