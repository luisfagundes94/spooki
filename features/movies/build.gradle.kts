import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.luisfelipe.buildSrc.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "${rootProject.rootDir}/base_dependencies.gradle")

android {

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = Versions.minSdk

        buildConfigField("String", "API_KEY", "\"${getApiKey()}\"")
        buildConfigField("String", "API_BASE_URL", "\"https://imdb-api.com/API/\"")
    }
}

fun getApiKey() = gradleLocalProperties(rootDir).getProperty("API_KEY")

