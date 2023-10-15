package com.pablojuice.rayw.feature.rent_create.di

import com.pablojuice.core.data.remote.api.factory.ApiFactory
import com.pablojuice.rayw.feature.rent_create.data.remote.api.RentCreateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RentCreateModule {

    @Provides
    fun provideRentCreateApi(apiFactory: ApiFactory): RentCreateApi = apiFactory.get()
}