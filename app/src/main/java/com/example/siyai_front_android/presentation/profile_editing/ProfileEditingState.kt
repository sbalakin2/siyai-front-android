package com.example.siyai_front_android.presentation.profile_editing

import com.example.siyai_front_android.presentation.auth.email_confirmation.RegState

sealed interface ProfileEditingState {
    data object Success : ProfileEditingState
    data class Error(val code: Int, val message: String) : ProfileEditingState
    data class Exception(val message: String) : ProfileEditingState
    data object Idle : ProfileEditingState
}