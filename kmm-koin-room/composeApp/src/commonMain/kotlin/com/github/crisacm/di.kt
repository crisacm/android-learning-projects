package com.github.crisacm

import com.github.crisacm.database.getRoomDatabase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) =
  startKoin {
    config?.invoke(this)
    modules(
      platformModule(),
      provideDatabaseModule,
    )
  }

val provideDatabaseModule = module {
  single { getRoomDatabase(get()) }
}