package com.example.siyai_front_android.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.siyai_front_android.domain.dto.CacheContainer
import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.domain.repositories.ProfileStorageRepository
import com.example.siyai_front_android.utils.parseISODateTime
import com.example.siyai_front_android.utils.toISODateTimeString
import kotlinx.coroutines.flow.first
import java.util.Date
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = "user_profile")

class ProfileStorageRepositoryImpl @Inject constructor(
    context: Context
) : ProfileStorageRepository {

    private val dataStore = context.dataStore

    override suspend fun getUserProfile(): Result<CacheContainer<Profile>> {
        val preferences =  dataStore.data.first()

        return runCatching {
            val cacheDate = preferences.getOrThrow(CACHE_DATE_TIME).parseISODateTime()

            val profile = Profile(
                email = preferences.getOrThrow(EMAIL),
                firstName = preferences.getOrThrow(NAME),
                lastName = preferences.getOrThrow(SURNAME),
                birthday = preferences.getOrThrow(BIRTHDAY),
                country = preferences.getOrThrow(COUNTRY),
                city = preferences.getOrThrow(CITY)
            )

            CacheContainer(cacheDate, profile)
        }
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
    }
}