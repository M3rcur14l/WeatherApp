package com.moneymour.openweather.openweather.data.remote.service

import com.moneymour.openweather.openweather.data.remote.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
interface OpenWeatherMapService {

    @GET("weather")
    fun getWeatherFromLocation(@Query("appid") appid: String,
                               @Query("units") units: String,
                               @Query("lat") latitude: Float,
                               @Query("lon") longitude: Float): Single<WeatherResponse>

}