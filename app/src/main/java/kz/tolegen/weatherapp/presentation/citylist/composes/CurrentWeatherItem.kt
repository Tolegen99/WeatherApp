package kz.tolegen.weatherapp.presentation.citylist.composes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.tolegen.weatherapp.data.local.CityEntity
import kz.tolegen.weatherapp.data.remote.dto.City
import kz.tolegen.weatherapp.domain.model.CurrentWeather


@Composable
fun CurrentWeatherItem(
    cityEntity: CityEntity?,
    onItemClick: (CityEntity) -> Unit
) {
    if (cityEntity != null) {
        Card(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp
                )
                .fillMaxWidth()
                .clickable { onItemClick(cityEntity) },
            elevation = 20.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cityEntity.name,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                )
                Text(
                    text = cityEntity.temp.toString() + "°C",
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }

    }
}