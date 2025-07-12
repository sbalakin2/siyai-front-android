package com.example.siyai_front_android.presentation.my_state.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.GetMyStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyStateViewModel @Inject constructor(
    private val getMyStateUseCase: GetMyStateUseCase,
) : ViewModel() {

    private val _mainState = MutableStateFlow<MainState>(MainState.Loading)
    val mainState: StateFlow<MainState> = _mainState.asStateFlow()

    init {
        viewModelScope.launch {
            val myState = getMyStateUseCase()
            _mainState.value = if (myState != null && myState.cycles.isNotEmpty()) {
                MainState.Main
            } else {
                MainState.Onboarding
            }
        }
    }
}