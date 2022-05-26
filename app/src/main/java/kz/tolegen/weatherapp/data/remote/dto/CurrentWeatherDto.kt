package kz.tolegen.weatherapp.data.remote.dto

import kz.tolegen.weatherapp.domain.model.CurrentWeather

data class CurrentWeatherDto(
    val cod: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val weather: List<Weather>,
    val wind: Wind
)

fun CurrentWeatherDto.toWeatherInfo() = CurrentWeather(
    id = id,
    main = main,
    name = name,
    weather = weather,
    wind = wind,
)