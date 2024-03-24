plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.pablojuice.core.common"
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

    compileOptions {
        sourceCompatibility = Configuration.Application.javaVersion
        targetCompatibility = Configuration.Application.javaVersion
    }

    kotlinOptions {
        jvmTarget = Configuration.Application.javaVersion.toString()
    }
}

dependencies {

    // Multithreading
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Firebase
    implementation("com.google.firebase:firebase-analytics-ktx:21.3.0")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.4.0")

    testImplementation(project(":test:unittest"))
}