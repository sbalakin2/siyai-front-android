package com.example.siyai_front_android.presentation.model

import com.example.siyai_front_android.R

enum class Categories{
    PURCHASED, ALL;
    fun toStringRes(): Int = when (this) {
        ALL -> R.string.all
        PURCHASED -> R.string.purchased
    }
}
