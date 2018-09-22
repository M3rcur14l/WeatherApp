package com.moneymour.openweather.openweather.di.module

import android.content.Context
import com.moneymour.openweather.openweather.Application
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}