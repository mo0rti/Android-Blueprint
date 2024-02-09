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
    implementation(project(":foundation"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))

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
