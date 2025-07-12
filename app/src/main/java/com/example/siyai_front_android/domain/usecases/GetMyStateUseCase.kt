package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.domain.dto.MyState

interface GetMyStateUseCase {

    suspend operator fun invoke(): MyState?
}