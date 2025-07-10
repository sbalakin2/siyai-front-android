package com.example.siyai_front_android.utils

import org.json.JSONObject
import retrofit2.Response

fun parseError(response: Response<*>): Pair<Int, String> {
    val errorBody = response.errorBody()?.string().orEmpty()
    return try {
        val json = JSONObject(errorBody)
        val httpResponse = json.optJSONObject("httpResponse")
        val code = httpResponse?.optInt("httpStatusCode") ?: 0
        val message = json.optString("message")
        code to message
    } catch (e: Exception) {
        0 to "Unknown error"
    }
}