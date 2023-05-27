package com.example.weather.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.search.domain.SearchUseCase

class SearchViewModel : ViewModel() {

    val searchUseCase = SearchUseCase()

    private val _data = MutableLiveData<String>()
    val data : LiveData<String> = _data

    suspend fun searchWeather() {
        searchUseCase(43.5, 45.5, "")
    }

}