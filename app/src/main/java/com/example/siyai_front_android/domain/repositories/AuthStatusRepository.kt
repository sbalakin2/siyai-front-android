package com.example.siyai_front_android.domain.repositories

import com.example.siyai_front_android.domain.dto.AuthProgress
import kotlinx.coroutines.flow.Flow

interface AuthStatusRepository {

    suspend fun isUserAuthorized(): Flow<Boolean>

    suspend fun getUserEmail(): String?

    suspend fun logIn(progress: AuthProgress)

    suspend fun logOut()
}