package com.example.siyai_front_android.presentation.welcome

import androidx.lifecycle.ViewModel
import com.example.siyai_front_android.domain.usecases.LetsMeetUseCase
import javax.inject.Inject

class LetsMeetViewModel @Inject constructor(
    private val letsMeetUseCase: LetsMeetUseCase
) : ViewModel() {

}