package com.github.crisacm

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.logger.Level
import org.koin.dsl.module

class KMMApp : Application(), KoinComponent {
  override fun onCreate() {
    super.onCreate()
    initKoin {
      androidLogger(Level.DEBUG)
      androidContext(this@KMMApp)
      modules(platformModule())
    }
  }
}