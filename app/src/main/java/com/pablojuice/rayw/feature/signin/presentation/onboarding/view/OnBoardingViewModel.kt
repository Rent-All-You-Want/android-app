package com.pablojuice.rayw.feature.signin.presentation.onboarding.view

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.presentation.basic.BasicViewModel
import com.pablojuice.rayw.feature.signin.domain.usecase.ProvideOnBoardingItemsUseCase
import com.pablojuice.rayw.feature.signin.presentation.onboarding.navigation.ToHomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val provideOnBoardingItems: ProvideOnBoardingItemsUseCase
) : BasicViewModel() {

    fun onOnBoardingViewed() {
        userPreferences.put(UserPreference.ON_BOARDING_VIEWED, true)
        submitNavigationEvent(ToHomeScreen())
    }

    fun provideItems() = provideOnBoardingItems()
}