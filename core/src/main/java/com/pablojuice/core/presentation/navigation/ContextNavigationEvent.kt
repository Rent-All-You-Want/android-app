package com.pablojuice.core.presentation.navigation

import android.content.Context
import androidx.navigation.NavController

abstract class ContextNavigationEvent(private val action: (Context) -> Unit) : NavigationEvent {
    override fun handle(controller: NavController) = action(controller.context)
}