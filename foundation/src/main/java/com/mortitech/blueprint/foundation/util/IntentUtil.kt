package com.mortitech.blueprint.foundation.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object IntentUtil {
    fun startDialIntent(context: Context, telNumber: String) {
        val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse(telNumber))
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun startViewIntent(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

    fun startEmailIntent(context: Context, email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$email")
        context.startActivity(Intent.createChooser(intent, "Email"))
    }

    fun openPlayStore(context: Context, packageName: String) {
        val playStoreIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("market://details?id=$packageName")
            setPackage("com.android.vending")
        }
        context.startActivity(playStoreIntent)
    }
}