package kz.tolegen.weatherapp.data.remote

import kz.tolegen.weatherapp.data.remote.dto.CurrentWeatherDto
import kz.tolegen.weatherapp.data.remote.dto.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("units") units: String= "metric",
    ): CurrentWeatherDto

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("units") units: String= "metric",
    ): WeatherForecastDto
}