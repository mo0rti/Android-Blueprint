package com.mortitech.blueprint.network.di

import com.mortitech.blueprint.network.service.authentication.AuthenticationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkApiModule {

    @Singleton
    @Provides
    fun provideAuthenticationApi(
        retrofitBuilder: Retrofit.Builder,
    ): AuthenticationApi {
        return retrofitBuilder
            .build()
            .create(AuthenticationApi::class.java)
    }
}
