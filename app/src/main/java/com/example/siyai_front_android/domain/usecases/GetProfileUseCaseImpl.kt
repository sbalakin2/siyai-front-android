package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.ProfileRepository
import com.example.siyai_front_android.presentation.profile.ProfileState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
) : GetProfileUseCase {
    override suspend fun invoke(email: String): ProfileState {
        return when (val result = profileRepository.getProfile(email)) {
            is NetworkResult.Success -> {
                ProfileState.Success(result.data)
            }
            is NetworkResult.Error -> {
                ProfileState.Error(result.code, result.message)
            }
            is NetworkResult.Exception -> {
                ProfileState.Exception(result.throwable.message.orEmpty())
            }
        }
    }
}