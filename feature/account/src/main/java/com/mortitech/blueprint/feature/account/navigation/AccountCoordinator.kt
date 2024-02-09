package com.mortitech.blueprint.feature.account.navigation

import com.mortitech.blueprint.feature.account.R
import com.mortitech.blueprint.feature.account.account.AccountCoordinatorEvent
import com.mortitech.blueprint.foundation.navigation.Coordinator
import com.mortitech.blueprint.foundation.navigation.StartDestination
import com.mortitech.blueprint.navigation.FeatureNavigator
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AccountCoordinator
@Inject
constructor(
    private val featureNavigator: FeatureNavigator
): Coordinator() {
    override fun onStart(): StartDestination {
        return StartDestination(R.id.navigation_home)
    }

    override fun onEvent(event: Any): Boolean {
        return when(event) {
            is AccountCoordinatorEvent.StartAuthFlow -> startAuthFlow()
            else -> false
        }
    }

    private fun startAuthFlow(): Boolean {
        activity?.startActivity(featureNavigator.accountFlow())
        activity?.finish()
        return true
    }

    override fun onPop() {
    }
}