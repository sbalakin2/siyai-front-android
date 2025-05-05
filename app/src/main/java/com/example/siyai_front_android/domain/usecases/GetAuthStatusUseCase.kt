package com.example.siyai_front_android.domain.usecases

import kotlinx.coroutines.flow.Flow

interface GetAuthStatusUseCase {
    operator fun invoke(): Flow<Boolean>
}