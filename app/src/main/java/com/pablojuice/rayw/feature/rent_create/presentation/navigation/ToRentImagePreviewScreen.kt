package com.pablojuice.rayw.feature.rent_create.presentation.navigation

import androidx.navigation.Navigator
import com.pablojuice.core.presentation.navigation.directional.DirectionalNavigationEvent
import com.pablojuice.rayw.feature.rent_create.presentation.view.CreateNewRentFragmentDirections

class ToRentImagePreviewScreen(extras: Navigator.Extras) :
    DirectionalNavigationEvent(
        CreateNewRentFragmentDirections.toImagePreview(), extras = extras
    )