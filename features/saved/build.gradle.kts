apply(from = "${rootProject.rootDir}/base_dependencies.gradle")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(project(":domain"))
}