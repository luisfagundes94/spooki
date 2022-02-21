apply(from = "${rootProject.rootDir}/base_dependencies.gradle")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        buildConfigField("String", "API_KEY", "\"${getApiKey()}\"")
        buildConfigField("String", "API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }
}

dependencies {
    implementation(project(":domain"))
}

fun getApiKey() = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty("API_KEY")