package com.example.siyai_front_android.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.siyai_front_android.domain.dto.CreateProfileData
import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.domain.repositories.UserProfileRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = "user_profile")

class UserProfileRepositoryImpl @Inject constructor(
    context: Context
) : UserProfileRepository {

    private val dataStore = context.dataStore

    override suspend fun getUserProfile(): Result<Profile> {
        val preferences =  dataStore.data.first()

        return try {
            val profile = Profile(
                email = preferences.getOrThrow(EMAIL),
                firstName = preferences.getOrThrow(NAME),
                lastName = preferences.getOrThrow(SURNAME),
                birthday = preferences.getOrThrow(BIRTHDAY),
                country = preferences.getOrThrow(COUNTRY),
                city = preferences.getOrThrow(CITY)
            )

            Result.success(profile)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveUserProfile(profile: CreateProfileData) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = profile.email
            preferences[NAME] = profile.name
            preferences[SURNAME] = profile.surName
            preferences[BIRTHDAY] = profile.birthday
            preferences[COUNTRY] = profile.country
            preferences[CITY] = profile.city
        }
    }

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private fun <T> Preferences.getOrThrow(key: Preferences.Key<T>): T {
        return get(key) ?: throw IllegalArgumentException("Key $key not found")
    }

    companion object {
        private val EMAIL = stringPreferencesKey("email")
        private val NAME = stringPreferencesKey("name")
        private val SURNAME = stringPreferencesKey("surName")
        private val BIRTHDAY = stringPreferencesKey("birthday")
        private val COUNTRY = stringPreferencesKey("country")
        private val CITY = stringPreferencesKey("city")
    }
}