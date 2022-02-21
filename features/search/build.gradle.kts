import com.luisfelipe.buildSrc.Versions
import com.luisfelipe.buildSrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "${rootProject.rootDir}/base_dependencies.gradle")

android {
    defaultConfig {
        minSdk = Versions.minSdk
    }
}

dependencies {
    implementation(project(":features:movies"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":commons-ui"))

    // UI
    implementation(Dependencies.UI.picasso)
}