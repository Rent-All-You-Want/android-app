package com.pablojuice.rayw.feature.home.presentation.navigation

import com.pablojuice.core.presentation.navigation.directional.DirectionalNavigationEvent
import com.pablojuice.rayw.MainGraphDirections

class ToRentDetails(rentId: String) :
    DirectionalNavigationEvent(MainGraphDirections.toRentDetails(rentId))