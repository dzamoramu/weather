package com.example.weather.search.data

import com.example.weather.search.data.network.SearchService

class SearchRepository {
    private val api = SearchService()

    suspend fun getWeatherByName(
        latitude: Double,
        longitude: Double,
        appId: String,
        language: String
    ) {
        return api.getWeatherByName(latitude, longitude, appId, language)
    }
}