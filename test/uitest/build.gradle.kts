plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.pablojuice.rayw.test.uitest"
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

    api("androidx.test:core:1.5.0")
    api("androidx.test.ext:junit-ktx:1.1.5")
    api("androidx.test:runner:1.5.2")
    api("androidx.test:rules:1.5.0")

    api("androidx.test.espresso:espresso-core:3.5.1")
    api("androidx.test.espresso:espresso-intents:3.5.1")
    api("androidx.test.espresso:espresso-web:3.5.1")
}