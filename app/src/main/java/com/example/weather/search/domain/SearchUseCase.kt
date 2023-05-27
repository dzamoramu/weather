package com.example.weather.search.domain

import com.example.weather.search.data.SearchRepository

class SearchUseCase {
   private val repository = SearchRepository()

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
        appId: String
    ) {
        return repository.getWeatherByName(latitude, longitude, appId)
    }
}