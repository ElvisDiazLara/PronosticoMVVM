package com.example.pronosticomvvm.data.db.unitlocalized

import androidx.room.ColumnInfo


data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "temperature")
    override val temperature: Double,
   // @ColumnInfo(name = "weather_code")
    //override val conditionText: String,
   // @ColumnInfo(name = "weather_icons")
    //override val conditionIconUrl: String,
    @ColumnInfo(name = "wind_speed")
    override val winSpeed: Double,
    @ColumnInfo(name = "wind_dir")
    override val winDirection: String,
    @ColumnInfo(name = "precip")
    override val precipitationVolume: Double,
    @ColumnInfo(name = "feelslike")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "visibility")
    override val visibilityDistance: Double

): UnitSpecificCurrentWeatherEntry