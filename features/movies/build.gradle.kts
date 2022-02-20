import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.luisfelipe.buildSrc.Versions
import com.luisfelipe.buildSrc.Dependencies

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
        buildConfigField("String", "API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }
}

dependencies {
    implementation(Dependencies.UI.shimmerEffect)
}

fun getApiKey() = gradleLocalProperties(rootDir).getProperty("API_KEY")

