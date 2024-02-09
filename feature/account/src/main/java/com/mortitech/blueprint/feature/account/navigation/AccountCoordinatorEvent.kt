package com.mortitech.blueprint.feature.account.account

import com.mortitech.blueprint.foundation.navigation.CoordinatorEvent

sealed class AccountCoordinatorEvent : CoordinatorEvent {
    object StartAuthFlow : AccountCoordinatorEvent()
}
