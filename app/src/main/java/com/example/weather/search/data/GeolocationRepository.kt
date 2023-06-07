package com.example.weather.search.data

import com.example.weather.search.data.network.GeoService
import com.example.weather.search.data.network.response.GeoCodingResponse

class GeolocationRepository {
    private val api = GeoService()

    suspend fun getGeolocationName(
        geolocationName: String
    ): List<GeoCodingResponse>? {
        return api.getGeolocationName(geolocationName)
    }
}