plugins {
    `kotlin-dsl`
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("script-runtime"))
}