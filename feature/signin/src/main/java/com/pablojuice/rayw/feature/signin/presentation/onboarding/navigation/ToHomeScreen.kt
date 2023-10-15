package com.pablojuice.rayw.feature.signin.presentation.onboarding.navigation

import com.pablojuice.core.presentation.navigation.directional.DirectionalNavigationEvent
import com.pablojuice.rayw.feature.signin.presentation.onboarding.view.OnBoardingFragmentDirections

internal class ToHomeScreen :
    DirectionalNavigationEvent(OnBoardingFragmentDirections.toMainGraphFlow())