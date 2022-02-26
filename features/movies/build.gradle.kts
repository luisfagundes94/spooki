import com.luisfelipe.buildSrc.Versions
import com.luisfelipe.buildSrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
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

    implementation(project(":domain"))
    implementation(project(":commons-testing"))
    implementation(project(":commons-ui"))

    // UI
    implementation(Dependencies.UI.shimmerEffect)
    implementation(Dependencies.UI.fadingEdgeLayout)
    implementation(Dependencies.UI.picasso)
}

