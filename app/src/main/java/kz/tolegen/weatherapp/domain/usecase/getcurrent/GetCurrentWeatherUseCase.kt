package kz.tolegen.weatherapp.domain.usecase.getcurrent

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.tolegen.weatherapp.common.Resource
import kz.tolegen.weatherapp.data.remote.dto.toWeatherInfo
import kz.tolegen.weatherapp.domain.model.CurrentWeather
import kz.tolegen.weatherapp.domain.repository.WeatherRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(cityName: String): Flow<Resource<CurrentWeather>> = flow {
        try {
            emit(Resource.Loading<CurrentWeather>())
            val currentWeather = repository.getCurrentWeather(cityName).toWeatherInfo()
            emit(Resource.Success<CurrentWeather>(currentWeather))
        } catch (e: HttpException) {
            emit(
                Resource.Error<CurrentWeather>(
                    e.localizedMessage ?: "Произошла непредвиденная ошибка."
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<CurrentWeather>("Не удалось подключиться к серверу. Проверьте подключение к Интернету."))
        }
    }
}