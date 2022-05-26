package kz.tolegen.weatherapp.presentation.citydetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kz.tolegen.weatherapp.presentation.citydetail.composes.WeatherForecastItem

@Composable
fun CityDetailScreen(
    viewModel: CityDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Text(
                        text = state.weatherForecast?.city?.name ?: "",
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.h6,
                    )
                    if (state.weatherForecast != null) {
                        Text(
                            text = "Прогноз погоды на 5 дней",
                            style = MaterialTheme.typography.body1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                bottom = 8.dp
                            )
                        )
                    }
                }
            }

            if (state.weatherForecast != null) {
                items(state.weatherForecast.forecastList) { forecast ->
                    WeatherForecastItem(
                        forecast = forecast
                    )
                }
            }
        }
    }
}

