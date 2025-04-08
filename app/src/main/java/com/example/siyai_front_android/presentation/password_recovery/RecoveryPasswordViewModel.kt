package com.example.siyai_front_android.presentation.password_recovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.RecoveryPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecoveryPasswordViewModel @Inject constructor(
    private val recoveryPasswordUseCase: RecoveryPasswordUseCase
) : ViewModel() {

    private val _recoveryPasswordState =
        MutableStateFlow<RecoveryPasswordState>(RecoveryPasswordState.Empty)
    val recoveryPasswordState: StateFlow<RecoveryPasswordState> =
        _recoveryPasswordState.asStateFlow()

    fun recoveryPassword(email: String) {
        viewModelScope.launch {
            _recoveryPasswordState.value = RecoveryPasswordState.Loading
            _recoveryPasswordState.value = runCatching { recoveryPasswordUseCase.invoke(email) }
                .getOrElse { RecoveryPasswordState.Exception(it.localizedMessage ?: "Unknown error") }
        }
    }

    fun resetState() {
        _recoveryPasswordState.value = RecoveryPasswordState.Empty
    }
}