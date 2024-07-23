package com.github.crisacm.animefinder.data.api.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

fun getInterceptor(): HttpLoggingInterceptor =
  HttpLoggingInterceptor { message -> Timber.i(message) }
