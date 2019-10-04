package com.example.pronosticomvvm.data.network

import androidx.lifecycle.LiveData
import com.example.pronosticomvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )

}