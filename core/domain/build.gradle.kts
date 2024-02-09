plugins {
    id("app.android.library")
    id("app.android.hilt")
    kotlin("kapt")
}

android {
    namespace = "com.mortitech.blueprint.core.domain"
}

dependencies {
    implementation(projects.foundation)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.model)

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
