package com.example.pronosticomvvm.data.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {



    val temperature: Double
    val windSpeed: Double
    val windDir: String
    val precip: Double
    val feelslike: Double
    val visibility: Double

}