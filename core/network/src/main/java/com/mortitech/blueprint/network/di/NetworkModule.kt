package com.mortitech.blueprint.network.di

import com.mortitech.blueprint.core.network.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .disableHtmlEscaping()
            .create()
    }

    @Singleton
    @Provides
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    fun provideBackendUrl(): String {
        return when (BuildConfig.BUILD_TYPE) {
            "debug" -> return BuildConfig.BACKEND_URL_DEV
            "acceptance" -> return BuildConfig.BACKEND_URL_ACC
            "release" -> return BuildConfig.BACKEND_URL
            else -> "Unknown build type"
        }
    }

    @Provides
    fun provideAuthInterceptorOkHttpClient(
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient,
    ): Retrofit.Builder {
        val backendUrl = provideBackendUrl()
        return Retrofit.Builder()
            .baseUrl(backendUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }
}