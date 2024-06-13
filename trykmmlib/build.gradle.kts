import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}


// ビルド情報の読み取り
val buildProperties = Properties()
buildProperties.load(FileInputStream(rootProject.file("trykmmlib/build.properties")))


kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release")
    }
    explicitApi = ExplicitApiMode.Strict

    val xcf = XCFramework("TryKMMLib")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "TryKMMLib"
            binaryOption("bundleId", "io.github.tshion")
            binaryOption("bundleShortVersionString", "${buildProperties["version_name"]}")
            binaryOption("bundleVersion", "${buildProperties["version_code"]}")

            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.kotlin.datetime)

            implementation(libs.ktor.client.contentNegotiation)
            api(libs.ktor.client.core)
            implementation(libs.ktor.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations.get("main").compilerOptions.options.freeCompilerArgs.add("-Xexport-kdoc")
    }
}

android {
    namespace = "io.github.tshion.trykmmlib"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

group = "io.github.tshion"
version = "${buildProperties["version_name"]}"

publishing {
    repositories {
        maven {
            name = "Develop"
            url = uri("${project.rootDir}/repo-maven")
        }

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/tshion/TryKMM")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
}
