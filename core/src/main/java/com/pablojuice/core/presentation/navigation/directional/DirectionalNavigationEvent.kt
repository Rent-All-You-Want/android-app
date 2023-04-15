package com.pablojuice.core.presentation.navigation.directional

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.utils.logging.Timber

abstract class DirectionalNavigationEvent(
    private val destination: NavDirections?,
    private val options: NavOptions? = null,
    private val extras: Navigator.Extras? = null
) : NavigationEvent {
    override fun handle(controller: NavController) {
        destination?.run {
            controller.currentDestination?.getAction(actionId)
                ?.also { controller.navigate(actionId, arguments, options, extras) }
                ?: Timber.e("Destination missing for ${controller.currentDestination} and $destination")
        } ?: Timber.e("Destination id is null")
    }
}