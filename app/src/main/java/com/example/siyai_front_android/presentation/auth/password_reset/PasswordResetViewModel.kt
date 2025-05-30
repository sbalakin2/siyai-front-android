package com.example.siyai_front_android.presentation.auth.password_reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.ResetPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PasswordResetViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
): ViewModel() {

    private val _passwordResetState = MutableStateFlow<PasswordResetState>(PasswordResetState.Idle)
    val passwordResetState: StateFlow<PasswordResetState> = _passwordResetState.asStateFlow()

    fun resetPassword(token: String, newPassword: String) {
        viewModelScope.launch {
            _passwordResetState.value = resetPasswordUseCase(token, newPassword)
        }
    }

    fun clearPasswordResetState() {
        _passwordResetState.value = PasswordResetState.Idle
    }
}