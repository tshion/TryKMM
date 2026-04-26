import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    androidLibrary {
        namespace = "io.github.tshion.devmenus"
        compileSdk {
            version = release(libs.versions.android.compileSdk.get().toInt()) {
                val minor = libs.versions.android.compileSdkMinor.get().toInt()
                minorApiLevel = minor.takeIf { 0 < it }
            }
        }
        minSdk {
            version = release(libs.versions.android.minSdk.get().toInt())
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        withHostTestBuilder {
        }
    }
    explicitApi = ExplicitApiMode.Strict

    val xcfName = "DevMenus"
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
            }
        }
        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.test.junit)
                implementation(libs.androidx.test.rules)
                implementation(libs.androidx.test.runner)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}
