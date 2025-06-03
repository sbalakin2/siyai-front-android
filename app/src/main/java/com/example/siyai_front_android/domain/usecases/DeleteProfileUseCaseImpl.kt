package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.repositories.DeleteProfileRepository
import com.example.siyai_front_android.presentation.profile_editing.DeleteProfileState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class DeleteProfileUseCaseImpl @Inject constructor(
    private val deleteProfileRepository: DeleteProfileRepository
) : DeleteProfileUseCase {
    override suspend fun invoke(email: String): DeleteProfileState =
        when (val result = deleteProfileRepository.deleteProfile(email)) {
            is NetworkResult.Success -> DeleteProfileState.Success(result.data)
            is NetworkResult.Error -> DeleteProfileState.Error(result.message)
            is NetworkResult.Exception -> DeleteProfileState.Error(result.throwable.message.orEmpty())
        }
}
