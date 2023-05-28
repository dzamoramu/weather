package com.example.weather.search.data.network

import com.example.weather.search.data.network.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchClient {

    @GET("data/2.5/weather")
    suspend fun getWeatherByName(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("appId") appId: String
    ): Response<SearchResponse>
}