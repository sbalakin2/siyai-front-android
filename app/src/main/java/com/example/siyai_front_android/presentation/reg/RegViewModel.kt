package com.example.siyai_front_android.presentation.reg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.RegUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegViewModel @Inject constructor(
    private val regUseCase: RegUseCase
): ViewModel() {

    private val _regState = MutableStateFlow<RegState>(RegState.Loading)
    val regState: StateFlow<RegState> = _regState.asStateFlow()

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            _regState.value = regUseCase(email, password)
        }
    }
}
