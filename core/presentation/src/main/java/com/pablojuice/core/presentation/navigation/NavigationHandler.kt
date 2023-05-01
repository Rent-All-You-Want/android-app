package com.pablojuice.core.presentation.navigation

interface NavigationHandler {
    fun navigate(event: NavigationEvent)

    fun navigateBack()
}