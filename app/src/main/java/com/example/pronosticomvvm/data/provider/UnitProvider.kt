package com.example.pronosticomvvm.data.provider

import com.example.pronosticomvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}