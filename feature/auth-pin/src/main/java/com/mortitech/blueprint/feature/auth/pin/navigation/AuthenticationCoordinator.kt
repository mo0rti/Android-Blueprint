package com.mortitech.blueprint.feature.auth.pin.navigation

import com.mortitech.blueprint.feature.auth.pin.R
import com.mortitech.blueprint.feature.auth.pin.navigation.AuthenticationCoordinatorEvent.NavigateToForgotPassword
import com.mortitech.blueprint.feature.auth.pin.navigation.AuthenticationCoordinatorEvent.NavigateToSignUp
import com.mortitech.blueprint.feature.auth.pin.navigation.AuthenticationCoordinatorEvent.StartAccountFlow
import com.mortitech.blueprint.foundation.navigation.Coordinator
import com.mortitech.blueprint.foundation.navigation.StartDestination
import com.mortitech.blueprint.navigation.FeatureNavigator
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AuthenticationCoordinator
@Inject
constructor(
    private val featureNavigator: FeatureNavigator
): Coordinator() {
    override fun onStart(): StartDestination {
        return StartDestination(R.id.navigation_sign_in_pin)
    }

    override fun onEvent(event: Any): Boolean {
        return when(event) {
            is NavigateToSignUp -> navigateToSignUp()
            is StartAccountFlow -> startAccountFlow()
            is NavigateToForgotPassword -> navigateToForgotPassword()
            else -> false
        }
    }

    private fun navigateToSignUp(): Boolean {
        return true
    }

    private fun startAccountFlow(): Boolean {
        activity?.startActivity(featureNavigator.accountFlow())
        activity?.finish()
        return true
    }

    private fun navigateToForgotPassword(): Boolean {
        return true
    }

    override fun onPop() {
    }
}