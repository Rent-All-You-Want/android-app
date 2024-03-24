plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.pablojuice.rayw.test.unittest"
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

    api("junit:junit:4.13.2")
    api("org.jetbrains.kotlin:kotlin-test:1.8.22")
    api("org.jetbrains.kotlin:kotlin-test-junit:1.8.22")
    api("io.mockk:mockk:1.13.4")

    api("androidx.arch.core:core-testing:2.1.0") {
        exclude(group = "com.android.support", module = "support-compat")
        exclude(group = "com.android.support", module = "support-annotations")
        exclude(group = "com.android.support", module = "support-core-utils")
    }
}