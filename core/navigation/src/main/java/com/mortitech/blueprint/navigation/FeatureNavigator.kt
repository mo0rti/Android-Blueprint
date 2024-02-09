package com.mortitech.blueprint.navigation

import android.content.Intent

// An interface to navigate between different flows
interface FeatureNavigator {
    fun authEmailFlow(): Intent
    fun authPinFlow(): Intent
    fun mainFlow(): Intent
    fun accountFlow(): Intent
}
