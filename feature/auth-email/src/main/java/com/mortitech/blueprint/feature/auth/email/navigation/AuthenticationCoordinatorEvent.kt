package com.mortitech.blueprint.feature.auth.email.navigation

import com.mortitech.blueprint.foundation.navigation.CoordinatorEvent

sealed class AuthenticationCoordinatorEvent : CoordinatorEvent {
    object NavigateToSignUp : AuthenticationCoordinatorEvent()
    object NavigateToForgotPassword : AuthenticationCoordinatorEvent()
    object StartAccountFlow : AuthenticationCoordinatorEvent()
}
