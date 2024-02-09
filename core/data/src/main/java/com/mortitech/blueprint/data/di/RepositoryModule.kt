package com.mortitech.blueprint.data.di

import com.mortitech.blueprint.data.repository.authentication.AuthenticationRepository
import com.mortitech.blueprint.data.repository.authentication.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthenticationRepository(repository: AuthenticationRepositoryImpl): AuthenticationRepository
}
