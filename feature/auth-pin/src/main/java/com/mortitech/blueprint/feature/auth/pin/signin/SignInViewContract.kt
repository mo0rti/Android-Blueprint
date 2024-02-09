package com.mortitech.blueprint.feature.auth.pin.signin

import com.mortitech.blueprint.foundation.architecture.mvi.ViewAction
import com.mortitech.blueprint.foundation.architecture.mvi.ViewEvent
import com.mortitech.blueprint.foundation.architecture.mvi.ViewState

data class SignInViewState(
    val pin: String = "",
    val isLoading: Boolean = false
) : ViewState

sealed class SignInViewAction: ViewAction {
    data class PinChanged(val pin: String) : SignInViewAction()
    data class PinEntryFinished(val pin: String) : SignInViewAction()
}

sealed class SignInViewEvent : ViewEvent {
    data class ShowError(val message: String) : SignInViewEvent()
    object SuccessfulAuthentication : SignInViewEvent()
}
