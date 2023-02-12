package com.pablojuice.rayw.app.di

import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.app.config.DebugAppConfig
import com.pablojuice.core.app.config.ReleaseAppConfig
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.api.http.config.DebugNetworkConfig
import com.pablojuice.core.data.remote.api.http.config.NetworkConfig
import com.pablojuice.core.data.remote.api.http.config.ReleaseNetworkConfig
import com.pablojuice.core.data.remote.auth.TokenRequestInterceptor
import com.pablojuice.rayw.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppConfig(networkConfig: NetworkConfig): AppConfig =
        if (BuildConfig.DEBUG) DebugAppConfig(networkConfig) else ReleaseAppConfig(networkConfig)

    @Provides
    fun provideNetworkConfig(userPreferences: UserPreferences): NetworkConfig {
        val apiUrl = BuildConfig.API_URL
        val interceptor = TokenRequestInterceptor(userPreferences)
        return if (BuildConfig.DEBUG)
            DebugNetworkConfig(apiUrl, interceptor) else ReleaseNetworkConfig(apiUrl, interceptor)
    }
}