plugins {
    id("app.android.feature")
    id("app.android.library.jacoco")
}

android {
    namespace = "com.mortitech.blueprint.feature.auth.pin"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(projects.components.pinpad)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
}

kapt {
    correctErrorTypes = true
}