package com.moneymour.openweather.openweather.di.module

import com.moneymour.openweather.openweather.data.remote.service.OpenWeatherMapService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Named("openWeatherMapAppId")
    fun provideOpenWeatherMapAppId() = "0a9c4bb25b41d2e00aacc0fb8b24adfe"

    @Provides
    @Named("openWeatherMapUnit")
    fun provideOpenWeatherMapAppUnit() = "metric"

    @Singleton
    @Provides
    internal fun provideOpenWeatherMapService(retrofit: Retrofit) = retrofit.create(OpenWeatherMapService::class.java)

}