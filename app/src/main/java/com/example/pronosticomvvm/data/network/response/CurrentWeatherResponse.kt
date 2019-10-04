package com.example.pronosticomvvm.data.network.response

import com.example.pronosticomvvm.data.db.entity.CurrentWeatherEntry
import com.example.pronosticomvvm.data.db.entity.Location
import com.example.pronosticomvvm.data.db.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    val request: Request,
    val location: Location,
    @SerializedName("current")
    val  currentWeatherEntry: CurrentWeatherEntry
)