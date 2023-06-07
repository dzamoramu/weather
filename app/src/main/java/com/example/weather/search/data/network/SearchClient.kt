package com.example.weather.search.data.network

import com.example.weather.search.data.network.response.GeoCodingResponse
import com.example.weather.search.data.network.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchClient {

    @GET("data/2.5/weather")
    suspend fun getWeatherByName(
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("appId") appId: String = APP_ID,
        @Query("units") units: String = UNITS
    ): Response<SearchResponse>

    @GET("geo/1.0/direct")
    suspend fun getGeocoding(
        @Query("q") geolocationName: String,
        @Query("appId") appId: String = APP_ID,
    ): Response<List<GeoCodingResponse>>

    companion object {
        const val APP_ID = "4fe8dad564e25bfd113c98b6cb38d6e5"
        const val UNITS = "metric"
    }
}