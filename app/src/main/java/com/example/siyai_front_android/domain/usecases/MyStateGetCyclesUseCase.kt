package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Cycle
import kotlinx.coroutines.flow.StateFlow

interface MyStateGetCyclesUseCase {

    operator fun invoke(): StateFlow<List<Cycle>>
}