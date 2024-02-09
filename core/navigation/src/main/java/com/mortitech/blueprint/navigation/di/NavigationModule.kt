package com.mortitech.blueprint.navigation.di

import android.content.Context
import com.mortitech.blueprint.navigation.FeatureNavigator
import com.mortitech.blueprint.navigation.FeatureNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    fun provideFeatureNavigator(
        @ApplicationContext context: Context
    ): FeatureNavigator {
        return FeatureNavigatorImpl(context)
    }
}