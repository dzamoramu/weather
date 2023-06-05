package com.example.weather.search.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weather.ui.theme.WeatherTheme


@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {
    val data: String by searchViewModel.data.observeAsState("")
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (searchBar, cardView) = createRefs()

        SearchBar(modifier = Modifier
            .padding(14.dp)
            .constrainAs(searchBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onQueryChanged = {
                searchViewModel.geoLocationName(it)
            })
        CardDataLocation(modifier = Modifier
            .padding(20.dp)
            .constrainAs(cardView) {
                top.linkTo(searchBar.bottom)
            })
    }
}

@Composable
fun CardDataLocation(modifier: Modifier = Modifier) {
    Card(
        modifier.size(400.dp, 150.dp)
    ) {
        ConstraintLayout(modifier = Modifier.size(250.dp)) {
            val (nameCity, time, temperature, wind) = createRefs()
            Text(text = "Munich",
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp, start = 10.dp)
                    .sizeIn(100.dp)
                    .constrainAs(nameCity) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })

            Text(text = "Hora y Clima",
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 10.dp)
                    .sizeIn(100.dp)
                    .constrainAs(time) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    })

            Text(text = "Temperature",
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp, end = 10.dp)
                    .sizeIn(100.dp)
                    .constrainAs(temperature) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    })

            Text(text = "Wind",
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 10.dp)
                    .sizeIn(100.dp)
                    .constrainAs(wind) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showScreen() {
    WeatherTheme {
        SearchScreen(searchViewModel = SearchViewModel())
    }
}



