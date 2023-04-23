package com.pablojuice.core.presentation.navigation

import androidx.navigation.NavController

fun interface NavigationEvent {
    fun handle(controller: NavController)
}