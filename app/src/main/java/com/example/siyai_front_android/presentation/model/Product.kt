package com.example.siyai_front_android.presentation.model

import androidx.annotation.DrawableRes

data class Product(
    @DrawableRes val imageId: Int,
    val name: String,
    val price: Long
)
