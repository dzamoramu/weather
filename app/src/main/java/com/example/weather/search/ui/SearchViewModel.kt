package com.example.weather.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.search.data.network.response.GeoCodingResponse
import com.example.weather.search.data.network.response.SearchResponse
import com.example.weather.search.domain.location.LocationTracker
import com.example.weather.search.domain.usecase.GeolocationUseCase
import com.example.weather.search.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor( private val locationTracker: LocationTracker) : ViewModel() {
    private val searchUseCase = SearchUseCase()
    private val geolocationUseCase = GeolocationUseCase()

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    private val _data = MutableLiveData<SearchResponse>()
    val data: LiveData<SearchResponse> = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _geolocation: MutableLiveData<List<GeoCodingResponse>> = MutableLiveData()

    fun geoLocationName(name: String) {
        viewModelScope.launch {
            _geolocation.value = geolocationUseCase.invoke(name)
            _cityName.value = name
            searchWeather(getLatitude(), getLongitude())
        }
    }

    fun getLocationWeather() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let {
                searchWeather(it.latitude, it.longitude)
            }
        }
    }

    private fun searchWeather(latitude: Double?, longitude: Double?) {
        viewModelScope.launch {
            _isLoading.value = false
            _data.value = searchUseCase.invoke(latitude, longitude)
            _isLoading.value = true

        }
    }

    fun getWeatherIconUrl(searchResponse: SearchResponse): String {
        val baseUrl = "https://openweathermap.org/img/wn/"
        val icon = searchResponse.weather_icon[0].icon
        return "$baseUrl$icon.png"
    }

    fun getLatitude(): Double? = _geolocation.value?.get(0)?.latitude

    fun getLongitude(): Double? = _geolocation.value?.get(0)?.longitude
}