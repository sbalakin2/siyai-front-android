package com.example.siyai_front_android.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.ExitFromAppUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val exitFromAppUseCase: ExitFromAppUseCase
) : ViewModel() {

    fun exitFromApp() {
        viewModelScope.launch {
            exitFromAppUseCase()
        }
    }
}