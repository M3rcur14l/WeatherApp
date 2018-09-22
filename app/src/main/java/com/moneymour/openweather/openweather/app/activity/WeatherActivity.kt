package com.moneymour.openweather.openweather.app.activity

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker.PERMISSION_GRANTED
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.moneymour.openweather.openweather.R
import com.moneymour.openweather.openweather.app.viewmodel.WeatherViewModel
import com.moneymour.openweather.openweather.data.WeatherRepository
import com.moneymour.openweather.openweather.data.remote.model.WeatherResponse
import com.moneymour.openweather.openweather.ext.bindToLifecycle
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_weather.*
import javax.inject.Inject

class WeatherActivity : DaggerActivity() {

    companion object {
        private const val PERMISSION_REQUEST: Int = 9999
    }

    @Inject
    lateinit var weatherRepository: WeatherRepository
    @Inject
    @JvmField
    var fusedLocationClient: FusedLocationProviderClient? = null
    lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        getUserPosition()
    }

    private fun getUserPosition() {
        fusedLocationClient?.let {
            if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {
                progress.visibility = VISIBLE
                it.lastLocation
                        .addOnSuccessListener { location -> onLocation(location) }
                        .addOnFailureListener { e -> onError(e) }
            } else
                requestPermission()
        } ?: requestPermission()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), PERMISSION_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST -> {
                AndroidInjection.inject(this) //Inject FusedLocationProviderClient
                getUserPosition()
            }
        }
    }

    private fun onLocation(location: Location) {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        weatherViewModel.getWeather(weatherRepository, location)
                .subscribe(
                        { onWeatherResult(weatherViewModel.weatherResponse) },
                        { e -> onError(e) })
                .bindToLifecycle(this)
    }

    @SuppressLint("SetTextI18n")
    private fun onWeatherResult(weatherResponse: WeatherResponse?) {
        progress.visibility = GONE
        weatherLayout.visibility = VISIBLE

        cityName.text = weatherResponse?.cityName
        val weather = weatherResponse?.weather?.firstOrNull()
        weather?.let {
            weatherDescription.text = it.condition
            Glide.with(weatherIcon.context)
                    .load("http://openweathermap.org/img/w/${it.icon}.png")
                    .into(weatherIcon)
        }
        weatherResponse?.weatherValues?.let {
            temperature.text = it.temperature?.toInt()?.toString() ?: ""
            temperatureMax.text = "${it.temperatureMax?.toInt()?.toString()}°"
            temperatureMin.text = "${it.temperatureMin?.toInt()?.toString()}°"
        }
    }

    private fun onError(e: Throwable) {
        progress.visibility = GONE
        Toast.makeText(this, "${e.javaClass.name}: ${e.message}", LENGTH_SHORT).show()
    }
}
