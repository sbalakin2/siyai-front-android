package com.example.siyai_front_android.utils

import android.util.Base64

fun createCredentials(email: String = "shine_admin", password: String = "p7UPX0}~;–LJr?_"): String {
    val credentials = "$email:$password"
    val encodedCredentials = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
    return "Basic $encodedCredentials"
}