package com.example.pronosticomvvm.data.repository

import androidx.lifecycle.LiveData
import com.example.pronosticomvvm.data.db.entity.Location
import com.example.pronosticomvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<Location>

}