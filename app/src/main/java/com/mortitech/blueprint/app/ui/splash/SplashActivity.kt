package com.mortitech.blueprint.app.ui.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mortitech.blueprint.app.navigation.AppCoordinator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    @Inject
    lateinit var coordinator: com.mortitech.blueprint.app.navigation.AppCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Keep the splash screen visible for this Activity
        installSplashScreen().setKeepOnScreenCondition { true }
        coordinator.activity = this
        makeApiCall()
    }

    private fun makeApiCall() = runBlocking {
        // Simulate an api call to get the configuration parameters from the server
        delay(1500)
        coordinator.start()
    }
}
