package com.moneymour.openweather.openweather.ext

import android.arch.lifecycle.GenericLifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Lifecycle.Event.*
import android.arch.lifecycle.Lifecycle.State.*
import android.arch.lifecycle.LifecycleOwner
import com.moneymour.openweather.openweather.app.viewmodel.RxViewModel
import io.reactivex.disposables.Disposable

fun Disposable.bindToLifecycle(lifecycleOwner: LifecycleOwner): Disposable {
    val lifecycle = lifecycleOwner.lifecycle
    val subscriptionState = lifecycle.currentState
    lifecycle.addObserver(GenericLifecycleObserver { _, e ->
        if (shouldDispose(subscriptionState, e))
            try {
                dispose()
            } catch (_: Exception) {
            }
    })
    return this
}

fun Disposable.bindToLifecycle(viewModel: RxViewModel): Disposable {
    viewModel.disposables.add(this)
    return this
}

private fun shouldDispose(subscriptionState: Lifecycle.State, stateEvent: Lifecycle.Event?) =
        when (stateEvent) {
            ON_PAUSE -> subscriptionState == STARTED || subscriptionState == RESUMED
            ON_STOP -> subscriptionState == CREATED
            ON_DESTROY -> true
            else -> false
        }




