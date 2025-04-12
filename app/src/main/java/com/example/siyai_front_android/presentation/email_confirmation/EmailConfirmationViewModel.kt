package com.example.siyai_front_android.presentation.email_confirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.VerifyUseCase
import com.example.siyai_front_android.domain.usecases.RegUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmailConfirmationViewModel @Inject constructor(
    private val verifyUseCase: VerifyUseCase,
    private val regUseCase: RegUseCase
): ViewModel() {

    private val _regState = MutableStateFlow<RegState>(RegState.Idle)
    val regState: StateFlow<RegState> = _regState.asStateFlow()

    private val _verificationState = MutableStateFlow<VerificationState>(VerificationState.Idle)
    val verificationState: StateFlow<VerificationState> = _verificationState.asStateFlow()

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            _regState.value = regUseCase(email, password)
        }
    }

    fun verify(email: String) {
        viewModelScope.launch {
            _verificationState.value = verifyUseCase(email)
        }
    }

    fun clearRegState() {
        _regState.value = RegState.Idle
    }

    fun clearVerificationState() {
        _verificationState.value = VerificationState.Idle
    }
}
