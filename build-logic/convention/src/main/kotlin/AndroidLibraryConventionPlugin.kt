import com.mortitech.blueprint.configureGradleManagedDevices
import com.mortitech.blueprint.configureKotlinAndroid
import com.mortitech.blueprint.configurePrintApksTask
import com.mortitech.blueprint.disableUnnecessaryAndroidTests
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.mortitech.blueprint.AppBuildType
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                // Configure Kotlin for Android
                configureKotlinAndroid(this)

                // Set default config options
                defaultConfig.targetSdk = 34

                // Configure product flavors if any
                /*
                configureFlavors(this)
                */

                // Configure managed devices for testing
                configureGradleManagedDevices(this)

                // Define the 'acceptance' build type
                buildTypes {
                    create("acceptance") {
                        initWith(getByName("release"))
                        signingConfig = signingConfigs.getByName("debug")
                        isMinifyEnabled = true
                    }
                }
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                configurePrintApksTask(this)
                disableUnnecessaryAndroidTests(target)
            }
            dependencies {
                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))
            }
        }
    }
}
