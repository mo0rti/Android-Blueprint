package com.mortitech.blueprint.network.di

import com.mortitech.blueprint.network.service.authentication.AuthenticationNetworkService
import com.mortitech.blueprint.network.service.authentication.AuthenticationNetworkServiceMockImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkServiceModule {

    @Binds
    abstract fun bindAuthenticationNetworkService(networkService: AuthenticationNetworkServiceMockImpl): AuthenticationNetworkService
}