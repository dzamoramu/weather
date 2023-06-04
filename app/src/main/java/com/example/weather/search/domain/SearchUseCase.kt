package com.example.weather.search.domain

import com.example.weather.search.data.SearchRepository
import com.example.weather.search.data.network.response.SearchResponse

class SearchUseCase {
   private val repository = SearchRepository()

    suspend operator fun invoke(
        latitude: Double?,
        longitude: Double?
    ): SearchResponse? {
        return repository.getWeatherByName(latitude, longitude)
    }
}