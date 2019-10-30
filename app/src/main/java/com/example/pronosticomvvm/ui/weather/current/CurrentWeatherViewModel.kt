package com.example.pronosticomvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.pronosticomvvm.data.provider.UnitProvider
import com.example.pronosticomvvm.data.repository.ForecastRepository
import com.example.pronosticomvvm.internal.UnitSystem
import com.example.pronosticomvvm.internal.lazyDeferred

class CurrentWeatherViewModel (
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
): ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem() //get from settings later

    val isMetric: Boolean
          get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }

    val weatherLocation by lazyDeferred{
        forecastRepository.getWeatherLocation()
    }
}
