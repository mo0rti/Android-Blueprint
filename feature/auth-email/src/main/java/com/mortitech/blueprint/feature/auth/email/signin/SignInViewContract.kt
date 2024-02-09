package com.mortitech.blueprint.feature.auth.email.signin

import com.mortitech.blueprint.foundation.architecture.mvi.ViewAction
import com.mortitech.blueprint.foundation.architecture.mvi.ViewEvent
import com.mortitech.blueprint.foundation.architecture.mvi.ViewState

data class SignInViewState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
) : ViewState

enum class SignInFieldType {
    EMAIL,
    PASSWORD,
}

sealed class SignInViewAction: ViewAction {
    data class FieldChanged(val fieldType: SignInFieldType, val value: Any) : SignInViewAction()
    object LoginButtonClicked : SignInViewAction()
}

sealed class SignInViewEvent : ViewEvent {
    data class ShowError(val message: String) : SignInViewEvent()
    object SuccessfulAuthentication : SignInViewEvent()
}
