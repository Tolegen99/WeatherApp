package kz.tolegen.weatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "city_table")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val temp: Double,
    val updatedUp: String
)
