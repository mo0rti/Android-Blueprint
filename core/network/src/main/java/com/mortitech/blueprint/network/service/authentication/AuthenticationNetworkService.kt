package com.mortitech.blueprint.network.service.authentication

import com.mortitech.blueprint.model.UserAccount

interface AuthenticationNetworkService {
    suspend fun signIn(email: String, password: String): UserAccount
    suspend fun signUp(email: String, password: String): UserAccount
}
