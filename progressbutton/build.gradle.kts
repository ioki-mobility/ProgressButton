import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose)
    `maven-publish`
    signing
    alias(libs.plugins.nmcp)
}

kotlin {
    jvmToolchain(17)
    compilerOptions.jvmTarget = JvmTarget.JVM_1_8
}

android {
    compileSdk = 36
    namespace = "com.ioki.progressbutton"

    defaultConfig {
        minSdk = 21
    }

    buildFeatures {
        compose = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
}

group = "com.ioki.progressbutton"
version = "2.2.0-SNAPSHOT"
publishing {
    publications {
        register<MavenPublication>("release") {
            artifactId = "progressbutton"

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

    repositories {
        maven("https://central.sonatype.com/repository/maven-snapshots") {
            name = "SonatypeSnapshot"
            credentials {
                username = System.getenv("SONATYPE_USER")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }
}

signing {
    val signingKey = System.getenv("GPG_SIGNING_KEY")
    val signingPassword = System.getenv("GPG_SIGNING_PASSWORD")
    isRequired = hasProperty("GPG_SIGNING_REQUIRED")
    if (isRequired) useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

nmcp {
    publishAllPublicationsToCentralPortal {
        username = providers.environmentVariable("SONATYPE_USER")
        password = providers.environmentVariable("SONATYPE_PASSWORD")
        publishingType = "USER_MANAGED"
    }
}