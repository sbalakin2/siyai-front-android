package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.domain.repositories.ProfileEditingRepository
import com.example.siyai_front_android.domain.repositories.ProfileStorageRepository
import com.example.siyai_front_android.presentation.profile_editing.ProfileEditingState
import com.example.siyai_front_android.utils.NetworkResult
import javax.inject.Inject

class EditProfileUseCaseImpl @Inject constructor(
    private val profileEditingRepository: ProfileEditingRepository,
    private val profileStorageRepository: ProfileStorageRepository
) : EditProfileUseCase {
    override suspend fun invoke(
        email: String,
        firstName: String,
        lastName: String,
        birthday: String,
        country: String,
        city: String
    ): ProfileEditingState {
        val result = profileEditingRepository.editProfile(
            email = email,
            firstName = firstName,
            lastName = lastName,
            birthday = birthday,
            country = country,
            city = city
        )
        return when (result) {
            is NetworkResult.Success -> {
                // save local
                val profile = Profile(firstName, lastName, birthday, email, country, city)
                profileStorageRepository.saveUserProfile(profile)

                ProfileEditingState.Success
            }
            is NetworkResult.Error -> {
                ProfileEditingState.Error(result.code, result.message)
            }
            is NetworkResult.Exception -> {
                ProfileEditingState.Exception(result.throwable.message.orEmpty())
            }
        }
    }
}