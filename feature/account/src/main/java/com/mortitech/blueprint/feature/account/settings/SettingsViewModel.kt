package com.mortitech.blueprint.feature.account.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "You can find your settings here"
    }
    val text: LiveData<String> = _text
}