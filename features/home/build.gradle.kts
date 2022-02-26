import com.luisfelipe.buildSrc.Versions

apply(from = "${rootProject.rootDir}/base_dependencies.gradle")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    defaultConfig {
        minSdk = Versions.minSdk
    }
}

dependencies {
    implementation(project(":features:movies"))
    implementation(project(":commons-ui"))
}