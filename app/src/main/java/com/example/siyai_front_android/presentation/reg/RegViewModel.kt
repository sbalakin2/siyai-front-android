package com.example.siyai_front_android.presentation.reg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.RegUseCase
import com.example.siyai_front_android.utils.NetworkResult
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
            when (val result = regUseCase(email, password)) {
                is NetworkResult.Success -> {
                    _regState.value = RegState.Success
                }
                is NetworkResult.Error -> {
                    _regState.value = RegState.Error(code = result.code, message = result.message)
                }
                is NetworkResult.Exception -> {
                    _regState.value = RegState.Exception(result.throwable.message.orEmpty())
                }
            }
        }
    }
}
