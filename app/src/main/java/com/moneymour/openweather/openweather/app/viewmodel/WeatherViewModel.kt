package com.moneymour.openweather.openweather.app.viewmodel

import android.arch.lifecycle.ViewModel
import android.location.Location
import com.moneymour.openweather.openweather.data.WeatherRepository
import com.moneymour.openweather.openweather.data.remote.model.WeatherResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherViewModel : ViewModel() {

    var weatherResponse: WeatherResponse? = null

    fun getWeather(weatherRepository: WeatherRepository, location: Location) =
            weatherRepository.getWeatherFromLocation(location.latitude.toFloat(), location.longitude.toFloat())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess { r -> weatherResponse = r }
                    .ignoreElement()

}