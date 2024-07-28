package com.github.crisacm.animefinder.initializer

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    Timber.plant(Timber.DebugTree())
    Timber.d("Timber are initialized")
  }

  override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
