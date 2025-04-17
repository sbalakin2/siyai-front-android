package com.example.siyai_front_android.presentation.model

import com.example.siyai_front_android.R

data class TrackList(
    val id: Int,
    val name: String,
    val regularity: Regularity,
    val daysCount: Int,
    val isChecked: Boolean,
    val progress: Byte = 0
)

enum class Regularity {
    EVERY_DAY;

    fun toStringRes() = when (this) {
        EVERY_DAY -> R.string.every_day
    }

}
