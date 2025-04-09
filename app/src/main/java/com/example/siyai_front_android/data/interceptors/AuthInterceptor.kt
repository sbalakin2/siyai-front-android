package com.example.siyai_front_android.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header(
                "Authorization",
                "Basic c2hpbmVfYWRtaW46cDdVUFgwfX474oCTTEpyP18="
            )
            .build()
        return chain.proceed(modifiedRequest)
    }
}