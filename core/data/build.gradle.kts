plugins {
    id("app.android.library")
    id("app.android.hilt")
    kotlin("kapt")
}

android {
    namespace = "com.mortitech.blueprint.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(projects.foundation)
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.network)

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    /*
    testImplementation(projects.core.testing)
    */
}

kapt {
    correctErrorTypes = true
}
