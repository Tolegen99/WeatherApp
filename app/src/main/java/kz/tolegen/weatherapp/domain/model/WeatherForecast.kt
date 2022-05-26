package kz.tolegen.weatherapp.domain.model

import kz.tolegen.weatherapp.data.remote.dto.City

data class WeatherForecast(
    val cod: Int,
    val forecastList: List<Forecast>,
    val city: City
)