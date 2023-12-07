plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin)
    `maven-publish`
}

kotlin {
    jvmToolchain(17)
}

android {
    compileSdk = 34
    namespace = "com.ioki.progressbutton"

    defaultConfig {
        minSdk = 21
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions { jvmTarget = "1.8" }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.ioki-mobility"
            artifactId = "progressbutton"
            version = "1.3.2"

            pom {
                name.set("ProgressButton")
                description.set("An Button that uses a progress state as background")
                url.set("https://github.com/ioki-mobility/ProgressButton")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                organization {
                    name.set("ioki")
                    url.set("https://ioki.com")
                }
                developers {
                    developer {
                        name.set("Stefan 'StefMa' M.")
                        email.set("StefMaDev@outlook.com")
                        url.set("https://StefMa.guru")
                        organization.set("ioki")
                        organizationUrl.set("https://ioki.com")
                    }
                }
                scm {
                    url.set("https://github.com/ioki-mobility/ProgressButton")
                    connection.set("scm:git:git://github.com/ioki-mobility/ProgressButton.git")
                    developerConnection.set("scm:git:ssh://git@github.com:ioki-mobility/ProgressButton.git")
                }
            }

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
