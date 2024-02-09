import com.mortitech.blueprint.AppBuildType

plugins {
    id("app.android.application")
    id("app.android.hilt")
    id("app.android.application.firebase")
    id("com.google.relay") version("0.3.09")
    kotlin("kapt")
}

android {
    namespace = "com.mortitech.blueprint.app"
    defaultConfig {
        applicationId = "com.mortitech.blueprint"
        versionCode = 1
        versionName = "0.1.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "com.mortitech.blueprint.core.testing.AppTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        testOptions {
            animationsDisabled = true
            unitTests {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
            }
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = AppBuildType.DEV.applicationIdSuffix
            isDebuggable = true
        }
        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = AppBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }
        create("acceptance") {
            initWith(release)
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            applicationIdSuffix = AppBuildType.ACCEPTANCE.applicationIdSuffix
        }
        create("benchmark") {
            initWith(release)
            matchingFallbacks.add("release")
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles("benchmark-rules.pro")
            isMinifyEnabled = true
            applicationIdSuffix = AppBuildType.BENCHMARK.applicationIdSuffix
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(projects.foundation)
    implementation(projects.components.pinpad)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.navigation)

    implementation(projects.feature.authEmail)
    implementation(projects.feature.authPin)
    implementation(projects.feature.account)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.constraintlayout)

    implementation(libs.timber)
    implementation(libs.gson)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit4)
    testImplementation(libs.kotest.junit5)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.mockk)
    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}

kapt {
    correctErrorTypes = true
}
