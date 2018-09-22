package com.moneymour.openweather.openweather.di.module

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.support.v4.app.ActivityCompat
import android.support.v4.content.PermissionChecker.PERMISSION_GRANTED
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides

@Module
class ClientModule {

    @Provides
    internal fun provideFusedLocationProviderClient(context: Context) =
            if (isLocationPermissionAvailable(context))
                LocationServices.getFusedLocationProviderClient(context)
            else
                null

    private fun isLocationPermissionAvailable(context: Context) =
            ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED
}

