package com.pablojuice.core.di

import android.content.Context
import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.api.factory.ApiFactory
import com.pablojuice.core.data.remote.api.factory.RetrofitApiFactory
import com.pablojuice.core.data.remote.api.http.NetworkUtils.createRetrofit
import com.pablojuice.core.data.remote.auth.UserManager
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Provides
    @Singleton
    fun provideApiFactory(retrofit: Retrofit): ApiFactory = RetrofitApiFactory(retrofit)

    @Provides
    @Reusable
    fun provideRetrofit(
        @ApplicationContext appContext: Context,
        appConfig: AppConfig,
        moshi: Moshi
    ): Retrofit = appContext.createRetrofit(appConfig.networkConfig, moshi)

    @Provides
    @Singleton
    fun provideTokenManager(preferences: UserPreferences) = UserManager(preferences)
}