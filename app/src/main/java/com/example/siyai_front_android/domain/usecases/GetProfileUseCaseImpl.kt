package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import com.example.siyai_front_android.domain.repositories.ProfileRepository
import com.example.siyai_front_android.domain.repositories.ProfileStorageRepository
import com.example.siyai_front_android.presentation.profile.ProfileState
import com.example.siyai_front_android.utils.NetworkResult
import com.example.siyai_front_android.utils.isHoursPassed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(
    private val authStatusRepository: AuthStatusRepository,
    private val profileRemoteRepository: ProfileRepository,
    private val profileStorageRepository: ProfileStorageRepository
) : GetProfileUseCase {

    private fun profileStateFlow(): Flow<ProfileState> = flow {
        emit(ProfileState.Loading)

        val cachedProfile = profileStorageRepository.getUserProfileFlow().firstOrNull()
        val lastUpdate = profileStorageRepository.profileLastUpdateTime()

        // cache valid
        if (cachedProfile != null && lastUpdate != null && !lastUpdate.isHoursPassed(hours = 4)) {
            profileStorageRepository.getUserProfileFlow()
                .mapToProfileState()
                .collect(::emit)
            return@flow
        }

        val email = authStatusRepository.getUserEmail()
        if (email == null) {
            emit(ProfileState.Exception("Email not found. Please re-login"))
            return@flow
        }

        when (val result = profileRemoteRepository.getProfile(email)) {
            is NetworkResult.Success -> {
                // save cache
                profileStorageRepository.saveUserProfile(result.data)

                profileStorageRepository.getUserProfileFlow()
                    .mapToProfileState()
                    .collect(::emit)
            }

            is NetworkResult.Error -> {
                val error = ProfileState.Error(result.code, result.message)
                emit(error)
            }

            is NetworkResult.Exception -> {
                val exception = ProfileState.Exception(result.throwable.message.orEmpty())
                emit(exception)
            }
        }
    }

    private fun Flow<Profile?>.mapToProfileState(): Flow<ProfileState> = flow {
        collect { profile ->
            val state = if (profile == null) {
                ProfileState.Exception("Profile is null")
            } else {
                ProfileState.Success(profile)
            }
            emit(state)
        }
    }

    override suspend fun invoke(): Flow<ProfileState> = profileStateFlow()
}