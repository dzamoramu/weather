package com.example.weather.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.search.domain.SearchUseCase
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val searchUseCase = SearchUseCase()

    private val _data = MutableLiveData<String>()
    val data : LiveData<String> = _data

     fun searchWeather() {
        viewModelScope.launch {
            searchUseCase.invoke(44.34, 10.99, "4fe8dad564e25bfd113c98b6cb38d6e5")
        }
    }
}