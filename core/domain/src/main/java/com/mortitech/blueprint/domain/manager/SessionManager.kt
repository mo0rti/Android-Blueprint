package com.mortitech.blueprint.domain.manager

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private var isLoggedIn: Boolean = false

    fun login() {
        isLoggedIn = true
    }

    fun logout() {
        isLoggedIn = false
    }

    fun isLoggedIn() = isLoggedIn
}