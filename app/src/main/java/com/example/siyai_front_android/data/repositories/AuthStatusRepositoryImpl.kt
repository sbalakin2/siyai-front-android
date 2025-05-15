package com.example.siyai_front_android.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.siyai_front_android.domain.dto.AuthProgress
import com.example.siyai_front_android.domain.repositories.AuthStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "auth")

class AuthStatusRepositoryImpl @Inject constructor(
    context: Context
) : AuthStatusRepository {

    private val dataStore = context.dataStore

    override suspend fun isUserAuthorized(): Flow<Boolean> {
        registrationAbortedCheck()

        return dataStore.data
            .map { preferences -> preferences[IS_AUTH] ?: false }
    }

    override suspend fun getUserEmail(): String? {
        val preferences =  dataStore.data.first()
        return preferences[EMAIL]
    }

    /**
     * Проверяет, была ли регистрация завершена до конца или нет
     */
    private suspend fun registrationAbortedCheck() {
        val currentPreferences = dataStore.data.first()

        val isRegister = currentPreferences[IS_REGISTER] ?: false
        val isAuth = currentPreferences[IS_AUTH] ?: false

        // регистрация прервана
        if (!isAuth && isRegister) {
            // продолжаем без дальнейших шагов после регистрации
            dataStore.edit { preferences -> preferences[IS_AUTH] = true }
        }
    }

    override suspend fun logIn(progress: AuthProgress) {
        when (progress) {
            // завершен шаг регистрации
            is AuthProgress.Register -> {
                dataStore.edit { preferences ->
                    preferences[IS_REGISTER] = true
                    preferences[EMAIL] = progress.email
                }
            }
            // может войти в приложение (логин)
            is AuthProgress.LogIn -> {
                dataStore.edit { preferences ->
                    preferences[IS_AUTH] = true
                    preferences[EMAIL] = progress.email
                }
            }
            // может войти в приложение (после регистрации)
            is AuthProgress.RegisterAndCreateProfile -> {
                dataStore.edit { preferences ->
                    preferences[IS_AUTH] = true
                    preferences[EMAIL] = progress.email
                }
            }
        }
    }

    override suspend fun logOut() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val IS_AUTH = booleanPreferencesKey("is_auth")
        private val IS_REGISTER = booleanPreferencesKey("is_register")

        private val EMAIL = stringPreferencesKey("email")
    }
}