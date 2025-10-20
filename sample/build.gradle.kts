plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose)
}

kotlin {
    jvmToolchain(17)
}

android {
    compileSdk = 36
    namespace = "com.ioki.progressbutton.sample"

    defaultConfig {
        applicationId = "com.ioki.progressbutton.sample"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":progressbutton"))

    implementation(libs.sample.material)
    implementation(libs.sample.composeActivity)

    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
}