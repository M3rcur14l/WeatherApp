package com.moneymour.openweather.openweather.di

import com.moneymour.openweather.openweather.Application
import com.moneymour.openweather.openweather.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Antonello Fodde on 22/09/18.
 * fodde.antonello@gmail.com
 */
@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    ClientModule::class,
    NetworkModule::class,
    ServiceModule::class])
interface AppComponent : AndroidInjector<Application> {

    override fun inject(app: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}