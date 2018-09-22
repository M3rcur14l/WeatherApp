package com.moneymour.openweather.openweather.data.remote

import com.moneymour.openweather.openweather.data.WeatherDataSource
import com.moneymour.openweather.openweather.data.remote.model.WeatherResponse
import com.moneymour.openweather.openweather.data.remote.service.OpenWeatherMapService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class WeatherRemoteDataSource @Inject constructor(
        @Named("openWeatherMapAppId")
        private val appId: String,
        @Named("openWeatherMapUnit")
        private val unit: String,
        private val weatherMapService: OpenWeatherMapService) : WeatherDataSource {

    override fun getWeatherFromLocation(latitude: Float, longitude: Float): Single<WeatherResponse> {
        return weatherMapService.getWeatherFromLocation(appId, unit, latitude, longitude)
    }
}