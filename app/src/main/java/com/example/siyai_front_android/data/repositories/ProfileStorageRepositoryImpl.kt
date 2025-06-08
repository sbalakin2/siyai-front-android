package com.example.siyai_front_android.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.domain.repositories.ProfileStorageRepository
import com.example.siyai_front_android.utils.parseISODateTime
import com.example.siyai_front_android.utils.toISODateTimeString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = "user_profile")

class ProfileStorageRepositoryImpl @Inject constructor(
    context: Context
) : ProfileStorageRepository {

    private val dataStore = context.dataStore

    override suspend fun profileLastUpdateTime(): Date? {
        val preferences = dataStore.data.first()
        return runCatching { preferences.getOrThrow(CACHE_DATE_TIME).parseISODateTime() }
            .getOrNull()
    }

    override suspend fun getUserProfileFlow(): Flow<Profile?> {
        return dataStore.data
            .map { preferences -> preferences.getProfile() }
            .map { result -> result.getOrNull() }
    }

    private fun Preferences.getProfile(): Result<Profile> = kotlin.runCatching {
        Profile(
            email = getOrThrow(EMAIL),
            firstName = getOrThrow(NAME),
            lastName = getOrThrow(SURNAME),
            birthday = getOrThrow(BIRTHDAY),
            country = getOrThrow(COUNTRY),
            city = getOrThrow(CITY),
            photo = getOrThrow(USER_PHOTO)
        )
    }

    override suspend fun saveUserProfile(profile: Profile) {
        dataStore.edit { preferences ->
            preferences[CACHE_DATE_TIME] = Date().toISODateTimeString()

            preferences[EMAIL] = profile.email
            preferences[NAME] = profile.firstName
            preferences[SURNAME] = profile.lastName
            preferences[BIRTHDAY] = profile.birthday
            preferences[COUNTRY] = profile.country
            preferences[CITY] = profile.city
            preferences[USER_PHOTO] = profile.photo
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
        private val CACHE_DATE_TIME = stringPreferencesKey("cache_date_time")

        private val EMAIL = stringPreferencesKey("email")
        private val NAME = stringPreferencesKey("name")
        private val SURNAME = stringPreferencesKey("surName")
        private val BIRTHDAY = stringPreferencesKey("birthday")
        private val COUNTRY = stringPreferencesKey("country")
        private val CITY = stringPreferencesKey("city")
        private val USER_PHOTO = stringPreferencesKey("photo")
    }
}