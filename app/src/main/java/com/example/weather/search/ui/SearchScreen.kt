package com.example.weather.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.weather.search.data.network.response.SearchResponse
import com.example.weather.search.data.network.response.TempData
import com.example.weather.search.data.network.response.WeatherIcon
import com.example.weather.search.data.network.response.WindData

@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {
    val data: SearchResponse by searchViewModel.data.observeAsState(
        SearchResponse(
            tempData = TempData(0.0f, 0.0f, 0.0f),
            wind = WindData(0.0f),
            weather_icon = listOf(WeatherIcon(""))
        )
    )
    val cityName: String by searchViewModel.cityName.observeAsState("")
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (searchBar, cardView) = createRefs()

        SearchBar(modifier = Modifier
            .padding(top = 40.dp, bottom = 14.dp, start = 20.dp, end = 20.dp)
            .constrainAs(searchBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onQueryChanged = {
                searchViewModel.geoLocationName(it)
            })
        val isLoading: Boolean by searchViewModel.isLoading.observeAsState(true)
        if (!isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            CardDataLocation(
                modifier = Modifier
                    .padding(20.dp)
                    .constrainAs(cardView) {
                        top.linkTo(searchBar.bottom)
                    }, data, cityName, searchViewModel
            )
        }
    }
}

@Composable
fun CardDataLocation(
    modifier: Modifier = Modifier,
    data: SearchResponse,
    cityName: String,
    viewModel: SearchViewModel
) {
    Card(
        modifier.fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .size(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.primaryVariant
                        )
                    )
                )
        ) {
            val (nameCity, temperature, image, wind, high, low) = createRefs()
            Text(text = cityName,
                fontStyle = FontStyle.Italic,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp, start = 10.dp)
                    .sizeIn(100.dp)
                    .constrainAs(nameCity) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
            val iconUrl = viewModel.getWeatherIconUrl(data)
            AsyncImage(
                model = iconUrl,
                contentDescription = "Weather Image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = 10.dp)
                    .constrainAs(image) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )
            Text(text = "${data.tempData.temp} °C",
                fontStyle = FontStyle.Italic,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp, end = 15.dp)
                    .sizeIn(100.dp)
                    .constrainAs(temperature) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    })
            Text(text = "H:${data.tempData.temp_max}°C",
                fontStyle = FontStyle.Italic,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .sizeIn(100.dp)
                    .constrainAs(high) {
                        top.linkTo(temperature.bottom)
                        end.linkTo(low.start)
                    })
            Text(text = "L:${data.tempData.temp_min}°C",
                fontStyle = FontStyle.Italic,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 6.dp, end = 5.dp)
                    .sizeIn(100.dp)
                    .constrainAs(low) {
                        top.linkTo(temperature.bottom)
                        end.linkTo(parent.end)
                    })
            Text(text = "${data.wind.speed} Wind",
                fontStyle = FontStyle.Italic,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 30.dp, end = 20.dp)
                    .sizeIn(100.dp)
                    .constrainAs(wind) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    })
        }
    }
}
