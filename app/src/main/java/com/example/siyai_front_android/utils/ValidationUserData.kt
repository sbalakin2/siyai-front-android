package com.example.siyai_front_android.utils

import android.content.Context
import com.example.siyai_front_android.R

fun validateUserData(
    email: String,
    password: String,
    repeatPassword: String? = null,
    context: Context,
    isRegistration: Boolean = false
): String? {
    return when {
        !email.isValidEmail() -> {
            context.getString(R.string.invalid_email)
        }
        !password.isValidPassword() -> {
            context.getString(R.string.invalid_password)
        }
        isRegistration && repeatPassword != password -> {
            context.getString(R.string.invalid_repeat_password)
        }
        else -> {
            null
        }
    }
}
