package com.example.siyai_front_android.presentation.reg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.VerifyUseCase
import com.example.siyai_front_android.presentation.email_confirmation.VerificationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegViewModel @Inject constructor(
    private val verifyUseCase: VerifyUseCase
): ViewModel() {

    private val _verificationState = MutableStateFlow<VerificationState>(VerificationState.Idle)
    val verificationState: StateFlow<VerificationState> = _verificationState.asStateFlow()

    fun verify(email: String) {
        viewModelScope.launch {
            _verificationState.value = verifyUseCase(email)
        }
    }
}
