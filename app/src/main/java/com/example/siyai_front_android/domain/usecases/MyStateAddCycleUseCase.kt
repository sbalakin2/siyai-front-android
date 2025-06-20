package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Cycle

interface MyStateAddCycleUseCase {

    suspend operator fun invoke(cycle: Cycle)
}