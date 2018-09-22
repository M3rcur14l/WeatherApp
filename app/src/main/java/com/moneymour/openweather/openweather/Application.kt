package com.moneymour.openweather.openweather

import com.moneymour.openweather.openweather.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Antonello Fodde on 22/09/18.
 * fodde.antonello@gmail.com
 */
class Application : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
            .builder()
            .application(this)
            .build()
}