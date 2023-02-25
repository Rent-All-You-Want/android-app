package com.pablojuice.core.presentation.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

abstract class NavigationEvent(
    val destination: NavDirections?,
    val options: NavOptions? = null
)