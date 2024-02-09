package com.mortitech.blueprint.feature.auth.email.signup

import com.mortitech.blueprint.foundation.architecture.mvi.ViewAction
import com.mortitech.blueprint.foundation.architecture.mvi.ViewEvent
import com.mortitech.blueprint.foundation.architecture.mvi.ViewState

data class SignUpViewState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false
) : ViewState

enum class SignUpFieldType {
    EMAIL,
    PASSWORD,
    CONFIRM_PASSWORD,
}

sealed class SignUpViewAction : ViewAction {
    data class FieldChanged(val fieldType: SignUpFieldType, val value: Any) : SignUpViewAction()
    object SubmitButtonClicked : SignUpViewAction()
}

sealed class SignUpViewEvent : ViewEvent {
    data class ShowError(val message: String) : SignUpViewEvent()
    object SuccessfulSignUp : SignUpViewEvent()
}