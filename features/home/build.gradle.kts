import com.luisfelipe.buildSrc.Versions

apply(from = "${rootProject.rootDir}/base_dependencies.gradle")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        minSdk = Versions.minSdk
    }
}