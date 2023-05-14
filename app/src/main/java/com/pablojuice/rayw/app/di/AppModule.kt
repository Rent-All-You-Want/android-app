package com.pablojuice.rayw.app.di

import android.content.Context
import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.app.config.AppInfo
import com.pablojuice.core.app.config.DebugAppConfig
import com.pablojuice.core.app.config.ReleaseAppConfig
import com.pablojuice.core.app.utils.getAppInfo
import com.pablojuice.core.data.remote.api.http.config.DebugNetworkConfig
import com.pablojuice.core.data.remote.api.http.config.NetworkConfig
import com.pablojuice.core.data.remote.api.http.config.ReleaseNetworkConfig
import com.pablojuice.core.data.remote.auth.UserManager
import com.pablojuice.rayw.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppConfig(networkConfig: NetworkConfig, appInfo: AppInfo): AppConfig =
        if (BuildConfig.DEBUG) DebugAppConfig(appInfo, networkConfig) else ReleaseAppConfig(
            appInfo,
            networkConfig
        )

    @Provides
    @Reusable
    fun provideAppInfo(@ApplicationContext appContext: Context): AppInfo = appContext.getAppInfo()

    @Provides
    @Reusable
    fun provideNetworkConfig(userManager: UserManager): NetworkConfig {
        val apiUrl = BuildConfig.API_URL
        val interceptor = userManager.tokenRequestInterceptor
        return if (BuildConfig.DEBUG)
            DebugNetworkConfig(apiUrl, interceptor) else ReleaseNetworkConfig(apiUrl, interceptor)
    }
}