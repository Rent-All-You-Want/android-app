import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import org.gradle.kotlin.dsl.extra
import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.Properties

object Application {
    val defaultFlavorDimensions = listOf("default")
    const val versionName = "0.0.1"

    const val minSdk = 23
    const val compileSdk = 34
    const val targetSdk = 34

    val javaVersion = JavaVersion.VERSION_17

    const val apiUrlType = "String"
    const val apiUrl = "API_URL"
    val localUrl: String
        get() {
            val url = "\"http://$localIp:8080/api/\""
            println("Local api url: $url")
            return url
        }
    const val stageUrl = "\"https://rayw-service.azurewebsites.net/api/\""
    const val prodUrl = "\"https://rayw-service.azurewebsites.net/api/\""

    val Project.lastCommitHash: String
        get() {
            val output = ByteArrayOutputStream()
            exec {
                commandLine("git", "rev-parse", "--short", "HEAD")
                standardOutput = output
            }
            return output.toString().trim().take(7).uppercase()
        }

    val Project.commitsCount: Int
        get() {
            val output = ByteArrayOutputStream()
            exec {
                commandLine("git", "rev-list", "--count", "HEAD")
                standardOutput = output
            }
            return output.toString().trim().toInt()
        }

    val localIp: String
        get() = NetworkInterface.getNetworkInterfaces().toList()
            .filter {
                it.isUp && !it.isLoopback && !it.isVirtual
                        && (it.name.contains("wlan") || it.name.contains("eth"))
            }.flatMap { networkInterface ->
                networkInterface.inetAddresses.toList()
                    .filter { address ->
                        !address.isLoopbackAddress && address is Inet4Address
                    }
                    .map { it.hostAddress }
            }.first().toString().trim()

    val Project.localProperties: Properties
        get() {
            val localProperties = Properties()
            if (rootProject.file(extra["local_properties_name"].toString()).exists()) {
                localProperties.load(
                    rootProject.file(extra["local_properties_name"].toString()).inputStream()
                )
            }
            return localProperties
        }
}