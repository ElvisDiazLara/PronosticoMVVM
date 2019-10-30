package com.example.pronosticomvvm.data.provider

import com.example.pronosticomvvm.data.db.WeatherLocationDao
import com.example.pronosticomvvm.data.db.entity.Location

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: Location): Boolean
    suspend fun getPreferredLocationString(): String
}