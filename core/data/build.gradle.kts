plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.devtoolsKSP)
}

android {
    namespace = "com.pablojuice.core.data"
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

    implementation(project(":core:common"))
    // Database
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")
    implementation("androidx.room:room-runtime:2.5.1")
    ksp("androidx.room:room-compiler:2.5.1")

    // Network
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.localebro:okhttpprofiler:1.0.8")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-moshi:2.9.0")
    api("com.squareup.moshi:moshi-kotlin:1.15.0")

    testImplementation(project(":test:unittest"))
}