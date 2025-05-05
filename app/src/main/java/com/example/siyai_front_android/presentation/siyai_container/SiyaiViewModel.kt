package com.example.siyai_front_android.presentation.siyai_container

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.usecases.EnterToAppUseCase
import com.example.siyai_front_android.domain.usecases.ExitFromAppUseCase
import com.example.siyai_front_android.domain.usecases.GetAuthStatusUseCase
import com.example.siyai_front_android.presentation.siyai_container.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SiyaiViewModel @Inject constructor(
    private val getAuthStatusUseCase: GetAuthStatusUseCase,
    private val enterToAppUseCase: EnterToAppUseCase,
    private val exitFromAppUseCase: ExitFromAppUseCase
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Route>(Route.Splash)
    val startDestination: StateFlow<Route> = _startDestination.asStateFlow()

    private val _keepSplashScreen = MutableStateFlow(true)
    val keepSplashScreen: StateFlow<Boolean> = _keepSplashScreen.asStateFlow()

    init {
        viewModelScope.launch {
            getAuthStatusUseCase()
                .collect { isAuth ->
                    _startDestination.value = if (isAuth) Route.Main else Route.Auth
                    _keepSplashScreen.value = false
                }
        }
    }

    fun enterToApp() {
        viewModelScope.launch { enterToAppUseCase() }
    }

    fun exitFromApp() {
        viewModelScope.launch { exitFromAppUseCase() }
    }
}