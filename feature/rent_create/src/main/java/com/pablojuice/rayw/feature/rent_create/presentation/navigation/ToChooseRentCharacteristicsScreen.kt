package com.pablojuice.rayw.feature.rent_create.presentation.navigation

import com.pablojuice.core.presentation.navigation.directional.AnimatedNavigationEvent
import com.pablojuice.core.presentation.navigation.directional.NavigationAnimation
import com.pablojuice.rayw.feature.rent_create.presentation.view.CreateNewRentFragmentDirections

class ToChooseRentCharacteristicsScreen : AnimatedNavigationEvent(
    destination = CreateNewRentFragmentDirections.toChooseRentCharacteristics(),
    navigationAnimation = NavigationAnimation.SlideToRight()
)