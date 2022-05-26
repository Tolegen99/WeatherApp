package kz.tolegen.weatherapp.presentation.citylist

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kz.tolegen.weatherapp.common.Resource
import kz.tolegen.weatherapp.data.local.CityDatabase
import kz.tolegen.weatherapp.data.local.CityEntity
import kz.tolegen.weatherapp.data.local.CityRepository
import kz.tolegen.weatherapp.domain.model.toIsoString
import kz.tolegen.weatherapp.domain.usecase.getcurrent.GetCurrentWeatherUseCase
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    application: Application
) : ViewModel() {

    private val _state = mutableStateOf(CityListState())
    val state: State<CityListState> = _state

    val readAllData: LiveData<List<CityEntity>>
    private val repository: CityRepository

    init {
        val cityDao = CityDatabase.getDatabase(application).cityDao()
        repository = CityRepository(cityDao)
        readAllData = repository.readAllData

        findCityAndCurrentWeather("Алматы")
        findCityAndCurrentWeather("Нур-Султан")
    }

    private fun addCity(city: CityEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCity(city)
        }
    }

    fun findCityAndCurrentWeather(cityName: String) {
        getCurrentWeatherUseCase(cityName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isContentReadyToShow = true,
                        isLoading = false
                    )
                    addCity(
                        CityEntity(
                            result.data!!.id,
                            result.data.name,
                            result.data.main.temp,
                            Date().toIsoString()
                        )
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        error = result.message ?: "Произошла непредвиденная ошибка",
                        isContentReadyToShow = true,
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = CityListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getWeatherOfCities(cities: List<CityEntity>) {
        cities.forEach {
            getCurrentWeatherUseCase(it.name).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        if (it == cities.last()) {
                            _state.value = CityListState(
                                isContentReadyToShow = true,
                                isLoading = false
                            )
                        }
                        addCity(
                            CityEntity(
                                result.data!!.id,
                                result.data.name,
                                result.data.main.temp,
                                Date().toIsoString()
                            )
                        )
                    }
                    is Resource.Error -> {
                        _state.value = CityListState(
                            error = result.message ?: "Произошла непредвиденная ошибка",
                            isContentReadyToShow = true
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CityListState(isContentReadyToShow = true, isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}