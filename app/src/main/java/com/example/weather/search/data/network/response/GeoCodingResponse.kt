package com.example.weather.search.data.network.response

import com.google.gson.annotations.SerializedName

data class GeoCodingResponse(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
)