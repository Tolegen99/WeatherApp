package kz.tolegen.weatherapp.domain.model

import kz.tolegen.weatherapp.data.remote.dto.Main
import kz.tolegen.weatherapp.data.remote.dto.Weather
import kz.tolegen.weatherapp.data.remote.dto.Wind
import java.text.SimpleDateFormat
import java.util.*

data class Forecast(
    val date: Date,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

fun String.isoToDate(): Date? {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this)
}

fun Date.toIsoString(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this)
}

fun Date.dateToStringFormat1(): String {
    return SimpleDateFormat("dd MMMM HH:mm:ss").format(this)
}