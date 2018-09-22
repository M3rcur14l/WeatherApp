package com.moneymour.openweather.openweather.di.module

import com.moneymour.openweather.openweather.app.activity.WeatherActivity
import com.moneymour.openweather.openweather.di.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun weatherActivity(): WeatherActivity

}
