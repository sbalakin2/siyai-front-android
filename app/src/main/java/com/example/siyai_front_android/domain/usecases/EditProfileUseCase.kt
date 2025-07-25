package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.profile_editing.ProfileEditingState

interface EditProfileUseCase {
    suspend operator fun invoke(
        email: String,
        firstName: String,
        lastName: String,
        birthday: String,
        country: String,
        city: String,
        photo: String
    ): ProfileEditingState
}