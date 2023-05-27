package com.example.weather.search.data.network

import com.example.weather.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getWeatherByName(
        latitude: Double,
        longitude: Double,
        appId: String,
        language: String
    ) {
        withContext(Dispatchers.IO) {
            retrofit.create(SearchClient::class.java).getWeatherByName(
                latitude,
                longitude,
                appId,
                language
            )
        }
    }
}