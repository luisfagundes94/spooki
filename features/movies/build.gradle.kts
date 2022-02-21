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
    }
}

dependencies {
    implementation(Dependencies.UI.shimmerEffect)
    implementation(project(":domain"))
    implementation(project(":commons-testing"))
    implementation(project(":commons-ui"))
}

