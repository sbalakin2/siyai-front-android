package com.example.siyai_front_android.domain.usecases

interface MyStateDeleteCycleUseCase {

    suspend operator fun invoke(id: Int)
}