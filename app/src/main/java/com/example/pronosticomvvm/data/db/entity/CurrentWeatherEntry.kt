package com.example.pronosticomvvm.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(


    //@SerializedName("weather_descriptions")
    //val weatherDescriptions: List<String>,
    val temperature: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    val precip: Double,
    val feelslike: Double,
    val visibility: Double


){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID

}