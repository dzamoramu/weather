package com.example.weather.search.domain

import com.example.weather.search.data.GeolocationRepository
import com.example.weather.search.data.network.response.GeoCodingResponse

class GeolocationUseCase {
    private val repository = GeolocationRepository()

    suspend operator fun invoke(
        geolocationName: String
    ): List<GeoCodingResponse>? {
        return repository.getGeolocationName(geolocationName)
    }
}