package com.example.weather.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.search.data.network.response.GeoCodingResponse
import com.example.weather.search.data.network.response.SearchResponse
import com.example.weather.search.domain.GeolocationUseCase
import com.example.weather.search.domain.SearchUseCase
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val searchUseCase = SearchUseCase()
    private val geolocationUseCase = GeolocationUseCase()

    private val _data = MutableLiveData<SearchResponse>()
    val data: LiveData<SearchResponse> = _data

    private val _geolocation: MutableLiveData<List<GeoCodingResponse>> = MutableLiveData()

    fun geoLocationName(name: String) {
        viewModelScope.launch {
            _geolocation.value = geolocationUseCase.invoke(name)
            searchWeather(getLatitude(), getLongitude(), name)
        }
    }

    private fun searchWeather(latitude: Double?, longitude: Double?, cityName: String) {
        viewModelScope.launch {
           _data.value  = searchUseCase.invoke(latitude, longitude)
        }
    }

    fun getLatitude(): Double? = _geolocation.value?.get(0)?.latitude

    fun getLongitude(): Double? = _geolocation.value?.get(0)?.longitude
}