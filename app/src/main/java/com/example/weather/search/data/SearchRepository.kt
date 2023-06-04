package com.example.weather.search.data

import com.example.weather.search.data.network.SearchService
import com.example.weather.search.data.network.response.SearchResponse

class SearchRepository {
    private val api = SearchService()

    suspend fun getWeatherByName(
        latitude: Double?,
        longitude: Double?
    ): SearchResponse? {
        return api.getWeatherByName(latitude, longitude)
    }
}