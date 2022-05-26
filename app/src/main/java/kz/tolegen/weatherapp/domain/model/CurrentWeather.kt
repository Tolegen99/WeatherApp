package kz.tolegen.weatherapp.domain.model

import kz.tolegen.weatherapp.data.remote.dto.*

data class CurrentWeather(
    val id: Int,
    val main: Main,
    val name: String,
    val weather: List<Weather>,
    val wind: Wind
)