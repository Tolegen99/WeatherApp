package kz.tolegen.weatherapp.data.remote.repository

import kz.tolegen.weatherapp.data.remote.Api
import kz.tolegen.weatherapp.data.remote.dto.CurrentWeatherDto
import kz.tolegen.weatherapp.data.remote.dto.WeatherForecastDto
import kz.tolegen.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: Api
) : WeatherRepository {
    override suspend fun getCurrentWeather(cityName: String): CurrentWeatherDto {
        return api.getCurrentWeather(cityName)
    }

    override suspend fun getWeatherForecast(cityName: String): WeatherForecastDto {
        return api.getWeatherForecast(cityName)
    }
}