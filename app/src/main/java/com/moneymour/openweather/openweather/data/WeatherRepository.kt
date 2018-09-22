package com.moneymour.openweather.openweather.data

import com.moneymour.openweather.openweather.data.remote.WeatherRemoteDataSource
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherRemoteDataSource: WeatherRemoteDataSource) : WeatherDataSource {

    override fun getWeatherFromLocation(latitude: Float, longitude: Float) =
            weatherRemoteDataSource.getWeatherFromLocation(latitude, longitude)

}