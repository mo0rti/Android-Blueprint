plugins {
    id("app.android.library")
    id("app.android.hilt")
    kotlin("kapt")
}

android {
    namespace = "com.mortitech.blueprint.core.navigation"
}

dependencies {
    implementation(project(":foundation"))
    implementation(project(":core:common"))

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)

    kapt(libs.hilt.compiler)

    /*
    testImplementation(projects.core.testing)
    */
}

kapt {
    correctErrorTypes = true
}
