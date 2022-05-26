package kz.tolegen.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)