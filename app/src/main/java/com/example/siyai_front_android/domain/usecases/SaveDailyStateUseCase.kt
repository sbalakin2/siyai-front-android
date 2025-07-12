package com.example.siyai_front_android.domain.usecases

interface SaveDailyStateUseCase {

    suspend operator fun invoke(state: Int?, note: String?)
}