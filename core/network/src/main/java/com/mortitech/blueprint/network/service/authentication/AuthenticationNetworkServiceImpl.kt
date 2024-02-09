package com.mortitech.blueprint.network.service.authentication

import com.mortitech.blueprint.model.UserAccount
import com.mortitech.blueprint.network.dto.AuthenticationRequestDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationNetworkServiceImpl
@Inject constructor(
    private val api: AuthenticationApi
): AuthenticationNetworkService {
    override suspend fun signIn(
        email: String,
        password: String
    ): UserAccount {
        val request = AuthenticationRequestDto(email, password)
        val response = api.signIn(request)
        return response.toUserAccount()
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): UserAccount {
        val request = AuthenticationRequestDto(email, password)
        val response = api.signUp(request)
        return response.toUserAccount()
    }
}
