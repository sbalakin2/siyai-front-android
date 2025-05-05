package com.example.siyai_front_android.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "auth")

class AuthStatusRepositoryImpl @Inject constructor(
    context: Context
) : AuthStatusRepository {

    private val dataStore = context.dataStore

    override fun authStatus(): Flow<Boolean> {
        return dataStore.data
            .map { preferences -> preferences[AUTH_STATE] ?: false }
    }

    override suspend fun logIn() {
        dataStore.edit { preferences ->
            preferences[AUTH_STATE] = true
        }
    }

    override suspend fun logOut() {
        dataStore.edit { preferences ->
            preferences[AUTH_STATE] = false
        }
    }

    companion object {
        private val AUTH_STATE = booleanPreferencesKey("auth_state")
    }
}