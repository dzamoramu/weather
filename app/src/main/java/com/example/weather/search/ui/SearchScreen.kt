package com.example.weather.search.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weather.ui.theme.WeatherTheme


@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {
    val data: String by searchViewModel.data.observeAsState("")
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        SearchBar(modifier = Modifier.padding(14.dp),
            onQueryChanged = {
                searchViewModel.geoLocationName(it)
            })
    }

}

@Preview(showBackground = true)
@Composable
fun showScreen() {
    WeatherTheme {
        SearchScreen(searchViewModel = SearchViewModel())
    }
}



