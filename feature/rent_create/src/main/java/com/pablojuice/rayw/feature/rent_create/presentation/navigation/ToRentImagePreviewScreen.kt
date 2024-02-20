package com.pablojuice.rayw.feature.rent_create.presentation.navigation

import androidx.navigation.Navigator
import com.pablojuice.core.presentation.navigation.directional.AnimatedNavigationEvent
import com.pablojuice.core.presentation.navigation.directional.NavigationAnimation
import com.pablojuice.rayw.feature.rent_create.presentation.view.CreateNewRentFragmentDirections

class ToRentImagePreviewScreen(extras: Navigator.Extras) : AnimatedNavigationEvent(
    destination = CreateNewRentFragmentDirections.toImagePreview(),
    navigationAnimation = NavigationAnimation.Fade(),
    extras = extras
)