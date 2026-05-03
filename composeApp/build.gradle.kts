import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.uiToolingPreview)
        }
        commonMain.dependencies {
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.uiToolingPreview)

            implementation(projects.devMenus)
            implementation(projects.trykmp)
        }
    }
}

android {
    namespace = "io.github.tshion.trykmp.sample"
    compileSdk {
        version = release(libs.versions.android.compileSdk.get().toInt()) {
            val minor = libs.versions.android.compileSdkMinor.get().toInt()
            minorApiLevel = minor.takeIf { 0 < it }
        }
    }
    defaultConfig {
        applicationId = "io.github.tshion.trykmp.sample"
        minSdk {
            version = release(libs.versions.android.minSdk.get().toInt())
        }
        targetSdk {
            version = release(libs.versions.android.targetSdk.get().toInt())
        }
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
}
