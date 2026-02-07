import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    id("maven-publish")
}


// ビルド情報の読み取り
val buildProperties = Properties()
buildProperties.load(FileInputStream(rootProject.file("trykmp/build.properties")))


kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
        publishLibraryVariants("release")
    }
    explicitApi = ExplicitApiMode.Strict

    val xcf = XCFramework("TryKMP")
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "TryKMP"
            binaryOption("bundleId", "io.github.tshion")
            binaryOption("bundleShortVersionString", "${buildProperties["version_name"]}")
            binaryOption("bundleVersion", "${buildProperties["version_code"]}")
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "io.github.tshion.trykmp"
    compileSdk {
        version = release(libs.versions.android.compileSdk.get().toInt()) {
            val minor = libs.versions.android.compileSdkMinor.get().toInt()
            minorApiLevel = minor.takeIf { 0 < it }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk {
            version = release(libs.versions.android.minSdk.get().toInt())
        }
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
    }
}
