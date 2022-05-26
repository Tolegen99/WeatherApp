package kz.tolegen.weatherapp.presentation.citydetail.composes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.tolegen.weatherapp.domain.model.Forecast
import kz.tolegen.weatherapp.domain.model.dateToStringFormat1


@Composable
fun WeatherForecastItem(
    forecast: Forecast?
) {
    if (forecast != null) {
        Card(
            modifier = Modifier
                .padding(
                    16.dp
                )
                .fillMaxWidth(),
            elevation = 20.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = forecast.date.dateToStringFormat1(),
                    style = MaterialTheme.typography.subtitle1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 4.dp,
                            bottom = 4.dp
                        ),
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = forecast.weather.first().description,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            bottom = 8.dp
                        ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Температура: ${forecast.main.temp}°C",
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        bottom = 8.dp
                    )
                )
                Text(
                    text = "Ощущается как: ${forecast.main.feelsLike}°C",
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        bottom = 8.dp
                    )
                )
                Text(
                    text = "Атмосферное давление: ${forecast.main.pressure}мм рт. ст.",
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        bottom = 8.dp
                    )
                )
                Text(
                    text = "Влажность: ${forecast.main.humidity}%",
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        bottom = 8.dp
                    )
                )
                Text(
                    text = "Скорость ветра: ${forecast.wind.speed}м/с",
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        bottom = 16.dp
                    )
                )
            }
        }

    }
}