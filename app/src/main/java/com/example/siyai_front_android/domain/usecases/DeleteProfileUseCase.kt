package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.presentation.profile_editing.DeleteProfileState

interface DeleteProfileUseCase {
    suspend operator fun invoke(email: String): DeleteProfileState
}
