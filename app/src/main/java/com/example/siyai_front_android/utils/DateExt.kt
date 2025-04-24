package com.example.siyai_front_android.utils

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toISODateString(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        .format(this)
}