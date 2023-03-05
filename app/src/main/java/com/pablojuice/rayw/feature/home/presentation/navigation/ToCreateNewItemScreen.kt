package com.pablojuice.rayw.feature.home.presentation.navigation

import com.pablojuice.core.presentation.navigation.AnimatedNavigationEvent
import com.pablojuice.core.presentation.navigation.NavigationAnimation
import com.pablojuice.rayw.feature.home.presentation.view.HomeFragmentDirections

class ToCreateNewItemScreen : AnimatedNavigationEvent(
    HomeFragmentDirections.actionHomeCreateItemGraph(),
    NavigationAnimation.Fade(launchSingleTop = true, restoreState = false)
)