package kz.tolegen.weatherapp.presentation.citylist

import kz.tolegen.weatherapp.domain.model.CurrentWeather

data class CityListState(
    val isLoading: Boolean = false,
    val isContentReadyToShow: Boolean = false,
    val error: String = ""
)