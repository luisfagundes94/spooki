plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        compileSdk = com.luisfelipe.buildSrc.Versions.compileSdk
    }
}

dependencies {
    // Testing
    implementation(com.luisfelipe.buildSrc.Dependencies.Test.mockk)
    implementation(com.luisfelipe.buildSrc.Dependencies.Test.junit)
    implementation(com.luisfelipe.buildSrc.Dependencies.Test.coroutines)
    implementation(com.luisfelipe.buildSrc.Dependencies.Test.archCore)
}
