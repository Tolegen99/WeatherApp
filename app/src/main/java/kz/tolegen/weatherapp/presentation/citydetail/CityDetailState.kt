package kz.tolegen.weatherapp.presentation.citydetail

import kz.tolegen.weatherapp.domain.model.WeatherForecast

data class CityDetailState(
    val isLoading: Boolean = false,
    val weatherForecast: WeatherForecast? = null,
    val error: String = "",
)