package com.example.pronosticomvvm.data.db.unitlocalized

import androidx.room.ColumnInfo

interface UnitSpecificCurrentWeatherEntry {

    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val winSpeed: Double
    val winDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double

}