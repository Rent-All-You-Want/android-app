package com.pablojuice.core.presentation.navigation

import androidx.navigation.NavController

class NavigationEvents {

    object Empty : NavigationEvent {
        override fun handle(controller: NavController) {
            /* no-op */
        }
    }

    object Back : NavigationEvent {
        override fun handle(controller: NavController) {
            controller.popBackStack()
        }
    }
}