package com.mortitech.blueprint.foundation.navigation

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

// Coordinator responsibilities:
// Kick starting the flow
// Store the states as user progresses through the flow such as user information
// Feature navigator is here to navigate between flows
abstract class Coordinator() {

    // NavController instead of FlowNavigator to navigate between screens inside the flow
    var navController: NavController? = null
    var activity: Activity? = null
    var navHostFragment: NavHostFragment? = null

    // Called by host when flow starts
    // To display the first screen, log analytics, setup state, etc.
    abstract fun onStart(): StartDestination

    // Handle events sent from view models and navigate to different screens
    // navController acts as flowNavigator for navigation with the help of navigation actions
    abstract fun onEvent(event: Any): Boolean

    // fire events when a screen pops from stack
    abstract fun onPop()

    open fun clear() {
        navController = null
        activity = null
        navHostFragment = null
    }
}