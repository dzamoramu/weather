package com.example.weather.search.data.network

import android.util.Log
import com.example.weather.core.network.RetrofitHelper
import com.example.weather.search.data.network.response.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getWeatherByName(
        latitude: Double,
        longitude: Double,
        appId: String
    ): SearchResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(SearchClient::class.java).getWeatherByName(
                latitude,
                longitude,
                appId
            )
            Log.d("esto es el response", response.body().toString())
            response.body()
        }
    }
}