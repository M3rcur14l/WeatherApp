package com.moneymour.openweather.openweather.data

import com.moneymour.openweather.openweather.data.remote.model.WeatherResponse
import io.reactivex.Single

interface WeatherDataSource {

    fun getWeatherFromLocation(latitude: Float,
                               longitude: Float): Single<WeatherResponse>

}