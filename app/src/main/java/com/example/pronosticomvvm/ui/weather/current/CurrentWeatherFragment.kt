package com.example.pronosticomvvm.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.pronosticomvvm.R
import com.example.pronosticomvvm.data.network.ApixuWeatherApiService
import com.example.pronosticomvvm.data.network.ConnectivityInterceptorImpl
import com.example.pronosticomvvm.data.network.WeatherNetworkDataSourceImpl
import com.example.pronosticomvvm.internal.glide.GlideApp
import com.example.pronosticomvvm.ui.base.ScopedFragment


import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import java.util.concurrent.locks.Condition

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {


    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)



        bindUI()

       // val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.context!!))
        //val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        //weatherNetworkDataSource.downloadedCurrentWeather.observe(this, Observer {
        //    textView1.text = it.toString()
        //})

        //GlobalScope.launch(Dispatchers.Main) {
          //  weatherNetworkDataSource.fetchCurrentWeather("London", languageCode = "en")

         //}


    }

    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()
        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(this@CurrentWeatherFragment, Observer { location->
            if (location == null) return@Observer
            updateLocation(location.name)
        })

        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null)return@Observer

            grup_loading.visibility = View.GONE

            updateToToday()
            updateTemperatures(it.temperature, it.feelslike)
            updatePrecipitation(it.precip)
            updateWind(it.windDir, it.windSpeed)
            updateVisibility(it.visibility)

            //GlideApp.with(this@CurrentWeatherFragment)
              //  .load("http:${it.icon}")
            //    .into(imageView_condition_icon)

        })

    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial:String): String{
        return if (viewModel.isMetric) metric else imperial
    }

    private fun updateLocation(location: String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateToToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Hoy"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
        textView_temperature.text = "$temperature$unitAbbreviation"
        textView_feels_like_temperature.text = "Sensación térmica $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String){
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "°in")
        textView_precipitation.text = "Precipitación: $precipitationVolume $unitAbbreviation"
    }

    private fun updateWind(winDirection: String, winSpeed: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_wind.text = "Viento: $winDirection, $winSpeed $unitAbbreviation"
    }

    private fun updateVisibility(visibilityDistance: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi")
        textView_visibility.text = "Visibilidad: $visibilityDistance $unitAbbreviation"
    }





}
