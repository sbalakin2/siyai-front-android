package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import com.example.siyai_front_android.domain.repositories.ProfileRepository
import com.example.siyai_front_android.domain.repositories.ProfileStorageRepository
import com.example.siyai_front_android.presentation.profile.ProfileState
import com.example.siyai_front_android.utils.NetworkResult
import com.example.siyai_front_android.utils.isHoursPassed
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(
    private val authStatusRepository: AuthStatusRepository,
    private val profileRemoteRepository: ProfileRepository,
    private val profileStorageRepository: ProfileStorageRepository
) : GetProfileUseCase {
    override suspend fun invoke(): ProfileState {
        val cachedProfile = profileStorageRepository.getUserProfile().getOrNull()

        // cache valid
        if (cachedProfile?.data != null && !cachedProfile.cacheDate.isHoursPassed(hours = 4)) {
            return ProfileState.Success(cachedProfile.data)
        }

        val email = authStatusRepository.getUserEmail()
            ?: return ProfileState.Exception("Email not found. Please re-login")

        return when (val result = profileRemoteRepository.getProfile(email)) {
            is NetworkResult.Success -> {
                // save cache
                profileStorageRepository.saveUserProfile(result.data)
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