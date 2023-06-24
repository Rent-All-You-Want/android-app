package com.pablojuice.rayw.feature.home.presentation.navigation

import com.pablojuice.core.presentation.MainGraphDirections
import com.pablojuice.core.presentation.navigation.directional.DirectionalNavigationEvent

class ToRentDetails(rentId: String) :
    DirectionalNavigationEvent(MainGraphDirections.toRentDetails(rentId))