package com.mortitech.blueprint.data.repository.authentication

interface AuthenticationRepository {
    suspend fun signin(email: String, password: String)
    suspend fun signin(pincode: String)
    suspend fun signup(email: String, password: String)
}