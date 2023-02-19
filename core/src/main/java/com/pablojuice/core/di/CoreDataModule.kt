package com.pablojuice.core.di

import android.content.Context
import com.pablojuice.core.data.converter.MoshiConverter
import com.pablojuice.core.data.converter.StringJsonConverter
import com.pablojuice.core.data.manager.EncryptedUserPreferences
import com.pablojuice.core.data.manager.UserPreferences
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {

    @Provides
    @Singleton
    fun provideUserPreferences(
        @ApplicationContext appContext: Context,
        jsonConverter: StringJsonConverter
    ): UserPreferences =
        EncryptedUserPreferences(context = appContext, jsonConverter = jsonConverter)

    @Provides
    @Reusable
    fun provideStringJsonConverter(moshi: MoshiConverter): StringJsonConverter = moshi

    @Provides
    @Reusable
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}