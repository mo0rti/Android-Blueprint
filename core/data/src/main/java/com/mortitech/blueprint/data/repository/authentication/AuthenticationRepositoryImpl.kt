package com.mortitech.blueprint.data.repository.authentication

import com.mortitech.blueprint.network.service.authentication.AuthenticationNetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepositoryImpl
@Inject
constructor(
    private val authService: AuthenticationNetworkService,
) : AuthenticationRepository {
    override suspend fun signin(email: String, password: String) {
        authService.signIn(email, password)
    }

    override suspend fun signin(pincode: String) {
        // TODO: Get the salt locally and check the hashed pincode with backemd
    }

    override suspend fun signup(email: String, password: String) {
        authService.signUp(email, password)
    }
}
