package com.pablojuice.rayw.feature.rent_create.presentation.navigation

import com.pablojuice.core.presentation.navigation.directional.AnimatedNavigationEvent
import com.pablojuice.core.presentation.navigation.directional.NavigationAnimation
import com.pablojuice.rayw.feature.rent_create.presentation.view.CreateNewRentFragmentDirections

class ToChooseRentPriceScreen : AnimatedNavigationEvent(
    destination = CreateNewRentFragmentDirections.toChooseRentPrice(),
    navigationAnimation = NavigationAnimation.SlideToRight()
)