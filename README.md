# Spooki
An Android application for browsing horror movies.

## Screenshots
<img src="/screenshots/screenshot1.jpg" width="200" /> <img src="/screenshots/screenshot2.jpg" width="200" /> <img src="/screenshots/screenshot3.jpg" width="200" />

## Stack
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - Official Kotlin's tooling for performing asynchronous work.
- [Koin](https://insert-koin.io/) - A smart Kotlin injection library
to keep you focused on your app, not on your tools 
- [Android Jetpack Components](https://developer.android.com/jetpack)
  * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - Navigation is a framework for navigating between screens within an Android application. 
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - An observable data holder class
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel is designed to store and manage UI-related data in a lifecycle conscious way.
  * [Room](https://developer.android.com/jetpack/androidx/releases/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
  * [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - View binding is a feature that allows you to more easily write code that interacts with views.
- [Picasso](https://square.github.io/picasso/) - An image loading library.
- [Gson](https://github.com/google/gson) - A Java library that can be used to convert Java Objects into their JSON representation.
- [OkHttp](https://github.com/square/okhttp) - An HTTP client for making network calls.
- [Retrofit](https://square.github.io/retrofit/) - A library for building REST API clients.
- [Ktlint](https://github.com/pinterest/ktlint) - A library for formatting Kotlin code according to official guidelines.
- [MockK](https://mockk.io/) - MockK is a mocking library for Kotlin.
- [JUnit4](https://junit.org/junit4/) - JUnit is a simple framework to write repeatable tests. 
- [Gradle's Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Gradle’s Kotlin DSL is an alternative syntax to the Groovy DSL with an enhanced editing experience.
- [buildSrc](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources) - A special module within the project to manage dependencies and whatnot.

## Development Setup
- Create an account in TMDB and include the API_KEY in the local.properties
- Download or clone this repository to your local machine.
- Download Android Studio
- Synchronize the project to download all dependencies.
- Start the application on a cell phone or emulator.
