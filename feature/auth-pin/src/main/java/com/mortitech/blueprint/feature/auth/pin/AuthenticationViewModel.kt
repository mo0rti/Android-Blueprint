package com.mortitech.blueprint.feature.auth.pin

import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewAction
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewEvent
import com.mortitech.blueprint.foundation.architecture.mvi.EmptyViewState
import com.mortitech.blueprint.foundation.architecture.mvi.MviViewModel

class AuthenticationViewModel: MviViewModel<
        EmptyViewState,
        EmptyViewAction,
        EmptyViewEvent>() {
    override fun processViewActions(action: EmptyViewAction) {
    }

    override fun initViewState() = EmptyViewState()
}
