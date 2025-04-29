package com.example.siyai_front_android.utils

import android.content.Context
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.example.siyai_front_android.R
import java.util.Date

fun validateUserData(
    email: String? = null,
    password: String? = null,
    repeatPassword: String? = null,
    context: Context
): String? {
    email?.let { if (!it.isValidEmail()) return context.getString(R.string.invalid_email)}
    password?.let { if (!it.isValidPassword()) return context.getString(R.string.invalid_password)}
    repeatPassword?.let {
        if (repeatPassword != password) return context.getString(R.string.invalid_repeat_password)}
    return null
}

fun checkIsFormCompleted(
    firstName: String,
    lastName: String,
    birthday: Date?,
    city: String,
    country: String
): Boolean {
    return sequenceOf(firstName, lastName, city, country)
        .all { it.isNotEmpty() } && birthday != null
}