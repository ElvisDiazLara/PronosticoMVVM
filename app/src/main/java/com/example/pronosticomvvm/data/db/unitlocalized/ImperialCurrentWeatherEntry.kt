package com.example.pronosticomvvm.data.db.unitlocalized

import androidx.room.ColumnInfo

data class ImperialCurrentWeatherEntry (


    override val temperature: Double,
    override val windSpeed: Double,
    override val windDir: String,
    override val precip: Double,
    override val feelslike: Double,
    override val visibility: Double

):UnitSpecificCurrentWeatherEntry