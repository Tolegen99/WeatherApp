package kz.tolegen.weatherapp.presentation.citydetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kz.tolegen.weatherapp.common.Constants
import kz.tolegen.weatherapp.common.Resource
import kz.tolegen.weatherapp.domain.usecase.getforecast.GetWeatherForecastUseCase
import javax.inject.Inject

@HiltViewModel
class CityDetailViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CityDetailState())
    val state: State<CityDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_CITY_NAME)?.let { cityName ->
            getWeatherForecast(cityName)
        }
    }

    private fun getWeatherForecast(cityName: String) {
        getWeatherForecastUseCase(cityName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CityDetailState(weatherForecast = result.data)
                }
                is Resource.Error -> {
                    _state.value = CityDetailState(
                        error = result.message ?: "Произошла непредвиденная ошибка"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CityDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}