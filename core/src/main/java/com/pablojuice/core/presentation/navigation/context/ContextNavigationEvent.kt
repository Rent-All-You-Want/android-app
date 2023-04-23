package com.pablojuice.core.presentation.navigation.context

import android.content.Context
import androidx.navigation.NavController
import com.pablojuice.core.presentation.navigation.NavigationEvent

abstract class ContextNavigationEvent(private val action: (Context) -> Unit) : NavigationEvent {
    override fun handle(controller: NavController) = action(controller.context)
}