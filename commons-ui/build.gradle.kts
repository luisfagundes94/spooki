import com.luisfelipe.buildSrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "${rootProject.rootDir}/base_dependencies.gradle")

android {
    defaultConfig {
        minSdk = com.luisfelipe.buildSrc.Versions.minSdk
    }
}

dependencies {
    implementation(project(":domain"))

    // UI
    implementation(Dependencies.UI.picasso)
}