package com.example.siyai_front_android.presentation.profile_editing

sealed class DeleteProfileState {
    data object Idle : DeleteProfileState()
    data class Success(val message: String) : DeleteProfileState()
    data class Error(val message: String) : DeleteProfileState()
}
