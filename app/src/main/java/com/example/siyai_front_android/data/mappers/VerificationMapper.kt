package com.example.siyai_front_android.data.mappers

import com.example.siyai_front_android.data.remote.dto.VerificationResponse
import com.example.siyai_front_android.domain.dto.Verification

fun VerificationResponse.toVerification() = Verification(
    expDate = expDate ?: 0,
    otp = code ?: 0
)
