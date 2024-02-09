package com.mortitech.blueprint.feature.auth.email

import androidx.activity.viewModels
import com.mortitech.blueprint.feature.auth.email.databinding.ActivityAuthenticationBinding
import com.mortitech.blueprint.feature.auth.email.navigation.AuthenticationCoordinator
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewAction
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewEvent
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewState
import com.mortitech.blueprint.foundation.architecture.mvi.MviCoordinatorActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationActivity :
    MviCoordinatorActivity<
            ActivityAuthenticationBinding,
            AuthenticationCoordinator,
            EmptyViewState,
            EmptyViewAction,
            EmptyViewEvent,
            AuthenticationViewModel>
    (ActivityAuthenticationBinding::inflate) {
    override val graphId: Int
        get() = R.navigation.authentication_graph
    override val navHostFragmentId: Int
        get() = R.id.nav_host_fragment_authentication
    override val toolbarId: Int
        get() = R.id.toolbar

    override val viewModel: AuthenticationViewModel by viewModels()

    override fun setupUI() {
    }

    @Inject
    override lateinit var coordinator: AuthenticationCoordinator

    override fun onViewStateChanged(viewState: EmptyViewState) {
    }

    override fun onViewEventReceived(viewEvent: EmptyViewEvent) {
    }
}