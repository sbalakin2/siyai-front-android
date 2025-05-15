package com.example.siyai_front_android.domain.dto

import java.util.Date

data class CacheContainer<T : Any>(
    val cacheDate: Date,
    val data: T?
)