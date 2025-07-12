package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.Daily

interface GetDailyStateUseCase {

    suspend operator fun invoke(): Daily?
}