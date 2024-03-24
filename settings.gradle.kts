import java.util.Properties

rootProject.name = "rayw"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials.username = "mapbox"

            val localProperties = Properties()

            localProperties.load(
                File(providers.gradleProperty("local_properties_name").get())
                    .inputStream()
            )
            credentials.password = localProperties.getProperty("MAPBOX_DOWNLOADS_TOKEN")
            authentication.create<BasicAuthentication>("basic")
        }
    }
}

include(":app")
include(":core:common")
include(":core:presentation")
include(":core:data")
include(":core:main")
include(":feature:chat")
include(":feature:devoptions")
include(":feature:home")
include(":feature:preferences")
include(":feature:profile")
include(":feature:rent_create")
include(":feature:rentlist")
include(":feature:notification")
include(":feature:signin")
include(":feature:wishlist")
include(":test:unittest")
include(":test:uitest")
