package com.moneymour.openweather.openweather.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(@SerializedName("name")
                           val cityName: String?,
                           val weather: List<Weather>?,
                           @SerializedName("main")
                           val weatherValues: WeatherValues?)

data class Weather(@SerializedName("main")
                   val condition: String?,
                   val description: String?,
                   val icon: String?)

data class WeatherValues(@SerializedName("temp")
                         val temperature: Float?,
                         @SerializedName("humidity")
                         val humidity: Float?,
                         @SerializedName("temp_min")
                         val temperatureMin: Float?,
                         @SerializedName("temp_max")
                         val temperatureMax: Float?)