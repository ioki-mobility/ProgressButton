@file:OptIn(ExperimentalEncodingApi::class)

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose)
    `maven-publish`
    signing
}

kotlin {
    jvmToolchain(17)
}

android {
    compileSdk = 35
    namespace = "com.ioki.progressbutton"

    defaultConfig {
        minSdk = 21
        targetSdk = 35
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
    kotlinOptions { jvmTarget = "1.8" }
}

dependencies {
    api(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
}

val base64EncodedBearerToken = Base64.encode(
    "${System.getenv("SONATYPE_USER")}:${System.getenv("SONATYPE_PASSWORD")}".toByteArray(),
)

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.ioki.progressbutton"
            artifactId = "progressbutton"
            version = "2.1.0-SNAPSHOT"

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
        maven("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/") {
            name = "SonatypeStaging"
            credentials(HttpHeaderCredentials::class) {
                name = "Authorization"
                value = "Bearer $base64EncodedBearerToken"
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }
}

signing {
    val signingKey = System.getenv("GPG_SIGNING_KEY")
    val signingPassword = System.getenv("GPG_SIGNING_PASSWORD")
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

tasks.register<Exec>("moveOssrhStagingToCentralPortal") {
    group = "publishing"
    description = "Runs after publishAllPublicationsToSonatypeStagingRepository to move the artifacts to the central portal"

    shouldRunAfter("publishAllPublicationsToSonatypeStagingRepository")

    commandLine = listOf(
        "curl",
        "-f",
        "-X", "POST",
        "-H", "Authorization: Bearer $base64EncodedBearerToken",
        "https://ossrh-staging-api.central.sonatype.com/manual/upload/defaultRepository/com.ioki",
    )
}