package com.example.siyai_front_android.utils

import android.util.Base64

fun createCredentials(email: String, password: String): String {
    val credentials = "$email:$password"
    val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
    return "Basic $encodedCredentials"
}