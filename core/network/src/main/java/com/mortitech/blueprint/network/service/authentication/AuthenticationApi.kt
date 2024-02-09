package com.mortitech.blueprint.network.service.authentication

import com.mortitech.blueprint.network.dto.AuthenticationRequestDto
import com.mortitech.blueprint.network.dto.UserAccountDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("signIn")
    fun signIn(
        @Body dto: AuthenticationRequestDto
    ): UserAccountDto

    @POST("signUp")
    fun signUp(
        @Body dto: AuthenticationRequestDto
    ): UserAccountDto
}

