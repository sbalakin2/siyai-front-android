package com.example.siyai_front_android.domain.repositories

import kotlinx.coroutines.flow.Flow

interface AuthStatusRepository {

    fun authStatus(): Flow<Boolean>

    suspend fun logIn()

    suspend fun logOut()
}