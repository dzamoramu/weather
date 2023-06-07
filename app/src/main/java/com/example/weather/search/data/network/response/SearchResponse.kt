package com.example.weather.search.data.network.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("main") val tempData: TempData,
    @SerializedName("wind") val wind: WindData,
    @SerializedName("weather") val weather_icon : List<WeatherIcon>
)

data class TempData(
    @SerializedName("temp") val temp: Float,
    @SerializedName("temp_min") val temp_min: Float,
    @SerializedName("temp_max") val temp_max: Float,
)

data class WindData(
    @SerializedName("speed") val speed: Float,
)

data class WeatherIcon(
    @SerializedName("icon") val icon: String
)
