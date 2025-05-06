package com.example.siyai_front_android.presentation.profile_editing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.dto.UserProfileData
import com.example.siyai_front_android.domain.usecases.GetUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileEditingViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _initialUserProfile = MutableStateFlow<UserProfileData?>(null)
    val initialUserProfile: StateFlow<UserProfileData?> = _initialUserProfile.asStateFlow()

    init {
        viewModelScope.launch {
            _initialUserProfile.value = getUserProfileUseCase()
                .fold(onSuccess = { it }, onFailure = { getEmptyUserProfileData() })
        }
    }

    private fun getEmptyUserProfileData(): UserProfileData {
        return UserProfileData(
            email = "",
            name = "",
            surName = "",
            birthday = "",
            country = "",
            city = ""
        )
    }
}