package com.example.pronosticomvvm

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.pronosticomvvm.data.db.ForecastDatabase
import com.example.pronosticomvvm.data.network.*
import com.example.pronosticomvvm.data.provider.LocationProvider
import com.example.pronosticomvvm.data.provider.LocationProviderImpl
import com.example.pronosticomvvm.data.provider.UnitProvider
import com.example.pronosticomvvm.data.provider.UnitProviderImpl
import com.example.pronosticomvvm.data.repository.ForecastRepository
import com.example.pronosticomvvm.data.repository.ForecastRepositoryImpl
import com.example.pronosticomvvm.ui.weather.current.CurrentWeatherViewModelFactory
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind()from singleton { ForecastDatabase(instance()) }
        bind()from singleton { instance<ForecastDatabase>().currentWeatherDao()}
        bind()from singleton { instance<ForecastDatabase>().weatherLocationDao()}
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind()from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}