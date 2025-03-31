package com.example.siyai_front_android.domain.usecases

import com.example.siyai_front_android.utils.NetworkResult

interface RegUseCase {
    suspend operator fun invoke(email: String, password: String): NetworkResult<Unit>
}
