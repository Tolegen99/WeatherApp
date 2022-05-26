package kz.tolegen.weatherapp.domain.usecase.getforecast

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.tolegen.weatherapp.common.Resource
import kz.tolegen.weatherapp.data.remote.dto.toWeatherForecast
import kz.tolegen.weatherapp.domain.model.WeatherForecast
import kz.tolegen.weatherapp.domain.repository.WeatherRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(cityName: String): Flow<Resource<WeatherForecast>> = flow {
        try {
            emit(Resource.Loading<WeatherForecast>())
            val weatherForecast = repository.getWeatherForecast(cityName).toWeatherForecast()
            emit(Resource.Success<WeatherForecast>(weatherForecast))
        } catch (e: HttpException) {
            emit(
                Resource.Error<WeatherForecast>(
                    e.localizedMessage ?: "Произошла непредвиденная ошибка."
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<WeatherForecast>("Не удалось подключиться к серверу. Проверьте подключение к Интернету."))
        }
    }
}