package kz.tolegen.weatherapp.data.local

import androidx.lifecycle.LiveData

class CityRepository(private val cityDao: CityDao) {

    val readAllData: LiveData<List<CityEntity>> = cityDao.readAllData()

    suspend fun addCity(city: CityEntity) {
        cityDao.addCity(city)
    }

}