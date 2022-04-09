import com.luisfagundes.buildSrc.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        compileSdk = com.luisfagundes.buildSrc.Versions.compileSdk
        minSdk = com.luisfagundes.buildSrc.Versions.minSdk
    }
}

dependencies {

    // Testing
    implementation(Dependencies.Test.mockk)
    implementation(Dependencies.Test.junit)
    implementation(Dependencies.Test.coroutines)
    implementation(Dependencies.Test.archCore)
}

