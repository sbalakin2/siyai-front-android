package com.example.siyai_front_android.utils

fun String.isValidEmail(): Boolean {
    return Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").matches(this)
}

fun String.isValidPassword(): Boolean {
    return this.length >= 8 &&
            this.any { it.isDigit() } &&
            this.any { it.isUpperCase() } &&
            this.any { it.isLowerCase() }
}