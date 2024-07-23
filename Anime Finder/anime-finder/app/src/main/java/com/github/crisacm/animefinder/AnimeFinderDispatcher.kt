package com.github.crisacm.animefinder

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val animeFinderDispatcher: AnimeFinderDispatcher)

enum class AnimeFinderDispatcher {
    IO,
}
