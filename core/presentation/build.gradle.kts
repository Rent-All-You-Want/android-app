plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.navigationSafeargs)
    kotlin("kapt")
}

android {
    namespace = "com.pablojuice.core.presentation"
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
        buildConfig = true
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

    implementation(project(":core:common"))

    // Android & UI
    api("androidx.activity:activity-ktx:1.8.2")
    api("androidx.fragment:fragment-ktx:1.6.2")
    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.10.0")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    api("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    api("jp.wasabeef:recyclerview-animators:4.0.2")

    // Image Processing
    api("com.github.bumptech.glide:glide:4.13.2")
    kapt("com.github.bumptech.glide:compiler:4.13.2")

    // Navigation
    api("androidx.navigation:navigation-fragment-ktx:2.7.6")
    api("androidx.navigation:navigation-ui-ktx:2.7.6")

    // Map
    api("com.google.android.gms:play-services-maps:18.2.0")
    //api("org.osmdroid:osmdroid-android:6.1.17")
    api("com.mapbox.maps:android:11.1.0")

    // Remove when lib bug is resolved
    implementation("com.github.tobrun.kotlin-data-compat:annotation:0.9.0")
    implementation("com.google.android.gms:play-services-location:21.1.0")

    testImplementation(project(":test:unittest"))
}