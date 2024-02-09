package com.mortitech.blueprint.feature.account

import androidx.activity.viewModels
import com.mortitech.blueprint.feature.account.databinding.ActivityAccountBinding
import com.mortitech.blueprint.feature.account.navigation.AccountCoordinator
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewAction
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewEvent
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewState
import com.mortitech.blueprint.foundation.architecture.mvi.MviCoordinatorActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountActivity :
    MviCoordinatorActivity<
            ActivityAccountBinding,
            AccountCoordinator,
            EmptyViewState,
            EmptyViewAction,
            EmptyViewEvent,
            AccountViewModel>
        (ActivityAccountBinding::inflate) {
    override val graphId: Int
        get() = R.navigation.account_graph
    override val navHostFragmentId: Int
        get() = R.id.nav_host_fragment_account
    override val toolbarId: Int
        get() = R.id.toolbar

    override val viewModel: AccountViewModel by viewModels()

    override fun setupUI() {
    }

    @Inject
    override lateinit var coordinator: AccountCoordinator

    override fun onViewStateChanged(viewState: EmptyViewState) {
    }

    override fun onViewEventReceived(viewEvent: EmptyViewEvent) {
    }
}