package com.mortitech.blueprint.feature.account

import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewAction
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewEvent
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewState
import com.mortitech.blueprint.foundation.architecture.mvi.MviViewModel

class AccountViewModel: MviViewModel<
        EmptyViewState,
        EmptyViewAction,
        EmptyViewEvent>() {
    override fun processViewActions(action: EmptyViewAction) {
    }

    override fun initViewState() = EmptyViewState()
}