package com.example.siyai_front_android.domain.dto

data class Cycle(
    val id: Int = 0,
    val start: Long,
    val end: Long,
    val isOnPeriod: Boolean = false
)
