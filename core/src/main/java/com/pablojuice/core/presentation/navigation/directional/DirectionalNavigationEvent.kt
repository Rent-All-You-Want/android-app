package com.pablojuice.core.presentation.navigation.directional

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.utils.logging.Timber

abstract class DirectionalNavigationEvent(
    private val destination: NavDirections?,
    private val options: NavOptions? = null
) : NavigationEvent {
    override fun handle(controller: NavController) {
        destination?.let { destination ->
            controller.currentDestination?.getAction(destination.actionId)
                ?.also { controller.navigate(destination, options) }
                ?: Timber.e("Destination missing for ${controller.currentDestination} and $destination")
        } ?: Timber.e("Destination id is null")
    }
}