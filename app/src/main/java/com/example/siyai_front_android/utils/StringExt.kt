package com.example.siyai_front_android.utils

import com.example.siyai_front_android.R

fun String.isValidEmail(): Boolean {
    return Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").matches(this)
}

fun String.isValidPassword(): Boolean {
    return this.length >= 8 &&
            this.any { it.isDigit() } &&
            this.any { it.isUpperCase() } &&
            this.any { it.isLowerCase() }
}

fun getDayWordForm(number: Int): Int {
    val n = number % 100
    return when {
        n in 11..14 -> R.string.day_form3
        else -> when (n % 10) {
            1 -> R.string.day_form1
            2, 3, 4 -> R.string.day_form2
            else -> R.string.day_form3
        }
    }
}
