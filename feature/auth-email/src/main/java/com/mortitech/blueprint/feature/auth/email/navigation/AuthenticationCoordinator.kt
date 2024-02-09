package com.mortitech.blueprint.feature.auth.email.navigation

import com.mortitech.blueprint.feature.auth.email.R
import com.mortitech.blueprint.feature.auth.email.navigation.AuthenticationCoordinatorEvent.NavigateToForgotPassword
import com.mortitech.blueprint.feature.auth.email.navigation.AuthenticationCoordinatorEvent.NavigateToSignUp
import com.mortitech.blueprint.feature.auth.email.navigation.AuthenticationCoordinatorEvent.StartAccountFlow
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
        return StartDestination(R.id.navigation_sign_in)
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
        navController?.navigate(R.id.action_loginFragment_to_signupFragment)
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