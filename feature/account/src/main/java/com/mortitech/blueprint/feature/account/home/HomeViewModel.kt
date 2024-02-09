package com.mortitech.blueprint.feature.account.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Your customizable Dashboard\n\nComing soon..."
    }
    val text: LiveData<String> = _text
}