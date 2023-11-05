package com.pablojuice.rayw.feature.rent_create.presentation.navigation

import com.pablojuice.core.presentation.navigation.directional.AnimatedNavigationEvent
import com.pablojuice.core.presentation.navigation.directional.NavigationAnimation
import com.pablojuice.rayw.feature.rent_create.presentation.view.CreateNewRentFragmentDirections

class ToChooseRentPledgeScreen : AnimatedNavigationEvent(
    destination = CreateNewRentFragmentDirections.toChooseRentPledge(),
    navigationAnimation = NavigationAnimation.SlideToRight()
)