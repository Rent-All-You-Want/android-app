package com.pablojuice.rayw.feature.signin.di

import com.pablojuice.core.data.remote.api.factory.ApiFactory
import com.pablojuice.rayw.feature.signin.data.remote.api.SignInApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SignInModule {

    @Provides
    fun provideSignInApi(apiFactory: ApiFactory): SignInApi = apiFactory.get()
}