plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin)
    `maven-publish`
}

android {
    compileSdk = 32
    namespace = "com.ioki.progressbutton"

    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.tools.compose.compiler.get().version
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.ioki-mobility"
            artifactId = "progressbutton"
            version = "1.1"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {
    api(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
}