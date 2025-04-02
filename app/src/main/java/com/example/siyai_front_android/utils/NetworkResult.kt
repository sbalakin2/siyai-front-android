package com.example.siyai_front_android.utils

sealed interface NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error<T>(val code: Int, val message: String) : NetworkResult<T>
    data class Exception<T>(val throwable: Throwable) : NetworkResult<T>
}
