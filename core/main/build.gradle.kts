plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidDaggerHilt)
    kotlin("kapt")
}

android {
    namespace = "com.pablojuice.core"
    compileSdk = Configuration.Application.compileSdk

    defaultConfig {
        minSdk = Configuration.Application.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        resValues = false
    }

    compileOptions {
        sourceCompatibility = Configuration.Application.javaVersion
        targetCompatibility = Configuration.Application.javaVersion
    }

    kotlinOptions {
        jvmTarget = Configuration.Application.javaVersion.toString()
    }
}

dependencies {

    api(project(":core:data"))
    api(project(":core:presentation"))
    api(project(":core:common"))

    // DI
    implementation(libs.hilt.android)
    api("androidx.hilt:hilt-navigation-fragment:1.1.0")
    kapt(libs.hilt.android.compiler)

    testImplementation(project(":test:unittest"))
}