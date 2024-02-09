package com.mortitech.blueprint.network.service.authentication

import com.mortitech.blueprint.model.UserAccount
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationNetworkServiceMockImpl
@Inject constructor(
): AuthenticationNetworkService {
    override suspend fun signIn(
        email: String, password: String
    ): UserAccount {
        return UserAccount(
            id = "1",
            email = email,
            firstName = "John",
            lastName = "Doe",
            profileImageLink = "https://picsum.photos/200"
        )
    }

    override suspend fun signUp(email: String, password: String): UserAccount {
        return UserAccount(
            id = "2",
            email = email,
            firstName = "Mary",
            lastName = "Doe",
            profileImageLink = "https://picsum.photos/200"
        )
    }
}
