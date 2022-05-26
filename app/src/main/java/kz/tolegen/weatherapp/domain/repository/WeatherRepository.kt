package kz.tolegen.weatherapp.domain.repository

import kz.tolegen.weatherapp.data.remote.dto.CurrentWeatherDto
import kz.tolegen.weatherapp.data.remote.dto.WeatherForecastDto

interface WeatherRepository {
    suspend fun getCurrentWeather(cityName: String): CurrentWeatherDto

    suspend fun getWeatherForecast(cityName: String): WeatherForecastDto
}