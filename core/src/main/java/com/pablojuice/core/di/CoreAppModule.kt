package com.pablojuice.core.di

import com.pablojuice.core.BuildConfig
import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.app.config.DebugAppConfig
import com.pablojuice.core.app.config.ReleaseAppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoreAppModule {

    @Provides
    fun provideAppConfig(): AppConfig =
        if (BuildConfig.DEBUG) DebugAppConfig() else ReleaseAppConfig()
}