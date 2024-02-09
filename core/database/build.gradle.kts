plugins {
    id("app.android.library")
    id("app.android.library.jacoco")
    id("app.android.hilt")
}

android {
    namespace = "com.mortitech.blueprint.core.database"
    defaultConfig {
        testInstrumentationRunner =
            "com.mortitech.blueprint.core.testing.AppTestRunner"
    }
}

dependencies {
    implementation(project(":foundation"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)

    /*
    androidTestImplementation(projects.core.testing)
    */
}
