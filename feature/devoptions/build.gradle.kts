plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidDaggerHilt)
    alias(libs.plugins.navigationSafeargs)
    kotlin("kapt")
}

android {
    namespace = "com.pablojuice.rayw.feature.devoptions"

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

    buildFeatures {
        viewBinding = true
    }

    kapt {
        correctErrorTypes = true
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

    implementation(project(":core:main"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(project(":test:unittest"))
    androidTestImplementation(project(":test:uitest"))
}