package com.pablojuice.rayw.feature.rent.presentation.list.navigation

import com.pablojuice.core.presentation.navigation.DirectionalNavigationEvent
import com.pablojuice.rayw.MainGraphDirections


class ToRentDetails(itemId: String) :
    DirectionalNavigationEvent(MainGraphDirections.toItemDetails(itemId))