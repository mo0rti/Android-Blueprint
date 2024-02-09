package com.mortitech.blueprint.navigation

import android.content.Context
import android.content.Intent

class FeatureNavigatorImpl(
    private val context: Context
): FeatureNavigator {
    override fun authEmailFlow() = internalIntent(context, "com.mortitech.blueprint.template.authentication.email.open")
    override fun authPinFlow() = internalIntent(context, "com.mortitech.blueprint.template.authentication.pin.open")
    override fun mainFlow() = internalIntent(context, "com.mortitech.blueprint.template.main.open")
    override fun accountFlow() = internalIntent(context, "com.mortitech.blueprint.template.account.open")
    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}
