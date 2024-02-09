plugins {
    id("app.android.library")
    id("app.android.library.jacoco")
    id("app.android.hilt")
}

android {
    namespace = "com.mortitech.blueprint.core.common"
}

dependencies {
    implementation(projects.foundation)

    implementation(libs.kotlinx.coroutines.android)
    /*
    testImplementation(projects.core.testing)
    */
}