import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    android {
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

        androidResources.enable = true
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
                implementation(libs.compose.components.resources)
                implementation(libs.compose.material3)
                implementation(libs.compose.navigation3.ui)
                implementation(libs.compose.uiToolingPreview)
                implementation(libs.compose.viewmodel)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.activity.compose)
                api(libs.androidx.fragment)
                implementation(libs.androidx.startup)
                implementation(libs.compose.uiToolingPreview)
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}

dependencies {
    "androidRuntimeClasspath"(libs.compose.uiTooling)
}
