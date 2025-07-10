package com.example.siyai_front_android.presentation.auth.email_confirmation

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

    private val _emailConfirmationState =
        MutableStateFlow<EmailConfirmationState>(EmailConfirmationState.Idle)
    val emailConfirmationState: StateFlow<EmailConfirmationState> =
        _emailConfirmationState.asStateFlow()

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            _emailConfirmationState.value = when (val result = regUseCase(email, password)) {
                RegState.Success -> {
                    EmailConfirmationState.RegSuccess
                }
                is RegState.Error -> {
                    EmailConfirmationState.Error(result.code, result.message)
                }
                is RegState.Exception -> {
                    EmailConfirmationState.Exception(result.message)
                }
                RegState.Idle -> {
                    EmailConfirmationState.Idle
                }
            }
        }
    }

    fun verify(email: String) {
        viewModelScope.launch {
            _emailConfirmationState.value = when (val result = verifyUseCase(email)) {
                is VerificationState.Success -> {
                    EmailConfirmationState.VerificationSuccess(result.expDate, result.otp)
                }
                is VerificationState.Error -> {
                    EmailConfirmationState.Error(result.code, result.message)
                }
                is VerificationState.Exception -> {
                    EmailConfirmationState.Exception(result.message)
                }
                VerificationState.Idle -> {
                    EmailConfirmationState.Idle
                }
            }
        }
    }

    fun clearEmailConfirmationState() {
        _emailConfirmationState.value = EmailConfirmationState.Idle
    }
}
