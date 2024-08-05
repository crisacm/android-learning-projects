
# Anime Finder

Anime Finder is an app to find information about Anime, developed to show the implementation of Jetpack Compose, Hilt, Navigation and Material 3, all this on the MVI architecture.

![Design banner](/media/anime-finder-design.png)

### What can the app do?

- [ ] Store Anime based on which Anime are synchronized
- [ ] List Animes using paging
- [ ] Search Animes locally and remotely
- [ ] View all related information when selecting an Anime
- [ ] Watch the Trailer

## Tech Stack

This application implements many of the most popular libraries in the Android ecosystem.

* [100% Kotlin](https://kotlinlang.org/)
    + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    + [Kotlin Flow](https://kotlinlang.org/docs/flow.html) - data flow across all app layers, including views
    + [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - parse [JSON](https://www.json.org/json-en.html)
* [Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when
      lifecycle state changes
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related
      data in a lifecycle-aware way
* [Firebase](https://firebase.google.com/?hl=es-419)
    * [Authentication](https://firebase.google.com/docs/auth?hl=es-419)
    * [Crahslitycs](https://firebase.google.com/docs/crashlytics?hl=es-419)
* [Koin](https://insert-koin.io/) - dependency injection (dependency retrieval)
* [Coil](https://github.com/coil-kt/coil) - image loading library
* [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
* MVVM + MVI (presentation layer)
* [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
  ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  , [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
  , [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
* [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* UI
    * [Jetpack Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit (used for Fragments)
    * [Material Design 3](https://m3.material.io/) - application design system providing UI components
    * Theme selection
        * [Dark Theme](https://material.io/develop/android/theming/dark) - dark theme for the app (Android 10+)
        * [Dynamic Theming](https://m3.material.io/styles/color/dynamic-color/overview) - use generated, wallpaper-based
          theme (Android 12+)
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - define build scripts
    * [Versions catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) - define dependencies
    * [Type safe accessors](https://docs.gradle.org/7.0/release-notes.html)

## Contribution 🤝

Please fork this repository and contribute back using pull requests.

❤️ Any contributions, large or small, major features, bug fixes, are welcomed and appreciated but will be thoroughly reviewed