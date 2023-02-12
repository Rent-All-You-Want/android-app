package com.pablojuice.core.di

import android.content.Context
import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.data.remote.api.factory.ApiFactory
import com.pablojuice.core.data.remote.api.factory.RetrofitApiFactory
import com.pablojuice.core.data.remote.api.http.NetworkUtils.createRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Provides
    fun provideApiFactory(retrofit: Retrofit): ApiFactory = RetrofitApiFactory(retrofit)

    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context, appConfig: AppConfig): Retrofit =
        appContext.createRetrofit(appConfig.networkConfig)
}