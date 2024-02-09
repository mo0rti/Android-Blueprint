package com.mortitech.blueprint.app.navigation

import com.mortitech.blueprint.domain.manager.SessionManager
import com.mortitech.blueprint.foundation.navigation.Coordinator
import com.mortitech.blueprint.foundation.navigation.StartDestination
import com.mortitech.blueprint.navigation.FeatureNavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppCoordinator
@Inject
constructor(
    private val featureNavigator: FeatureNavigator,
    private val sessionManager: SessionManager
): Coordinator() {
    override fun onStart(): StartDestination {
        throw IllegalArgumentException("App coordinator navigate to activities only")
    }

    fun start() {
        val intent = if (sessionManager.isLoggedIn()) {
            featureNavigator.accountFlow()
        } else {
            featureNavigator.authPinFlow()
        }
        activity?.finish()
        activity?.startActivity(intent)
    }

    override fun onEvent(event: Any): Boolean {
        return false
    }

    override fun onPop() {
    }
}
