package kz.tolegen.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import kz.tolegen.weatherapp.domain.model.Forecast
import kz.tolegen.weatherapp.domain.model.WeatherForecast
import kz.tolegen.weatherapp.domain.model.isoToDate
import java.text.SimpleDateFormat

data class WeatherForecastDto(
    val cod: Int,
    @SerializedName("list")
    val forecastDtoList: List<ForecastDto>,
    val city: City
)

fun WeatherForecastDto.toWeatherForecast() = WeatherForecast(
    cod = cod,
    forecastList = forecastDtoList.map {
        Forecast(
            date = it.dtTxt.isoToDate()!!,
            main = it.main,
            weather = it.weather,
            wind = it.wind
        )
    },
    city = city
)