import Configuration.Application.commitsCount
import Configuration.Application.localProperties
import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidDaggerHilt)
    alias(libs.plugins.navigationSafeargs)
    alias(libs.plugins.firebaseCrashlytics)
    kotlin("kapt")
}

android {

    namespace = "com.pablojuice.rayw"

    compileSdk = Configuration.Application.compileSdk

    defaultConfig {
        applicationId = namespace

        minSdk = Configuration.Application.minSdk
        targetSdk = Configuration.Application.targetSdk

        versionCode = commitsCount
        versionName = Configuration.Application.versionName
        resourceConfigurations += listOf("en", "uk")

        manifestPlaceholders["usesLocalBackEnd"] = false
    }

    signingConfigs {
        create("release") {
            val props = localProperties
            if (props.getProperty("certificate.keyPassword") != null) {
                storeFile = file(props.getProperty("certificate.path"))
                keyAlias = props.getProperty("certificate.keyAlias")
                keyPassword = props.getProperty("certificate.keyPassword")
                storePassword = props.getProperty("certificate.storePassword")
            }
        }
    }

    buildTypes {
        getByName("release") {
//            isDebuggable = true
            isMinifyEnabled = true
            isShrinkResources = true

            manifestPlaceholders["crashlyticsCollectionEnabled"] = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = if (localProperties.isEmpty) {
                signingConfigs.getByName("debug")
            } else signingConfigs.getByName("release")

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = true
            }
        }

        getByName("debug") {
            manifestPlaceholders["crashlyticsCollectionEnabled"] = false

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }
        }
    }

    flavorDimensions += Configuration.Application.defaultFlavorDimensions

    productFlavors {
        create("local") {
            buildConfigField(
                Configuration.Application.apiUrlType,
                Configuration.Application.apiUrl,
                Configuration.Application.localUrl
            )
            manifestPlaceholders["usesLocalBackEnd"] = true
        }

        create("stage") {
            buildConfigField(
                Configuration.Application.apiUrlType,
                Configuration.Application.apiUrl,
                Configuration.Application.stageUrl
            )
        }

        create("prod") {
            buildConfigField(
                Configuration.Application.apiUrlType,
                Configuration.Application.apiUrl,
                Configuration.Application.prodUrl
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

    lint {
        checkReleaseBuilds = false
    }
}


dependencies {

    implementation(project(":core:main"))

    implementation(project(":feature:devoptions"))
    implementation(project(":feature:home"))
    implementation(project(":feature:signin"))
    implementation(project(":feature:rentlist"))
    implementation(project(":feature:wishlist"))
    implementation(project(":feature:rent_create"))
    implementation(project(":feature:chat"))
    implementation(project(":feature:preferences"))
    implementation(project(":feature:notification"))
    implementation(project(":feature:profile"))

    implementation(libs.androidx.splashscreen)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(project(":test:unittest"))
    androidTestImplementation(project(":test:uitest"))
}