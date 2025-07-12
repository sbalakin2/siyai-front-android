package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Cycle

interface MyStateChangeCyclesUseCase {

    suspend operator fun invoke(cycles: List<Cycle>)
}