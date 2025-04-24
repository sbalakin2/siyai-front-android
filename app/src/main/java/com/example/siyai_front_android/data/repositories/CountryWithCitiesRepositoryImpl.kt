package com.example.siyai_front_android.data.repositories

import android.content.Context
import com.example.siyai_front_android.R
import com.example.siyai_front_android.domain.dto.CountryWithCities
import com.example.siyai_front_android.domain.repositories.CountryWithCitiesRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okio.use
import javax.inject.Inject


class CountryWithCitiesRepositoryImpl @Inject constructor(
    private val json: Json,
    private val context: Context
) : CountryWithCitiesRepository {

    @OptIn(ExperimentalSerializationApi::class)
    override fun getAllCountries(): List<CountryWithCities> {
        val countries = context.resources.openRawResource(R.raw.map_to_country_id_city_name)
            .buffered()
            .use { stream ->
                json.decodeFromStream<List<CountryItem>>(stream)
            }

        return countries
            .groupBy { it.id }
            .map { entry ->
                CountryWithCities(
                    id = entry.key,
                    name = entry.value.first().name,
                    cities = entry.value.map { it.city },
                )
            }
    }

    @Serializable
    private data class CountryItem(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String,
        @SerialName("city_name") val city: String
    )
}