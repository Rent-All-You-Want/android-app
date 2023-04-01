package com.pablojuice.core.presentation.navigation

import androidx.navigation.NavController

class NavigationEvents {

    object EmptyNavigationEvent : NavigationEvent {
        override fun handle(controller: NavController) {
            /* no-op */
        }
    }
}