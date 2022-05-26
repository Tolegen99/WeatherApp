package kz.tolegen.weatherapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(city: CityEntity)

    @Query("SELECT * FROM city_table ORDER BY name")
    fun readAllData(): LiveData<List<CityEntity>>
}