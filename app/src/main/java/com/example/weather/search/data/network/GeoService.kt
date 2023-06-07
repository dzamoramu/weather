package com.example.weather.search.data.network

import android.util.Log
import com.example.weather.core.network.RetrofitHelper
import com.example.weather.search.data.network.response.GeoCodingResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeoService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getGeolocationName(
        geolocationName: String,
    ): List<GeoCodingResponse>? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(SearchClient::class.java).getGeocoding(
                geolocationName
            )
            response.body()
        }
    }
}