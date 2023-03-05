package com.pablojuice.core.presentation.basic

import com.pablojuice.core.presentation.base.screen.BaseViewModel
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.navigation.NavigationEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BasicViewModel : BaseViewModel() {

    protected val _navigationEvents =
        MutableStateFlow<NavigationEvent>(NavigationEvents.EmptyNavigationEvent)
    val navigationEvents: StateFlow<NavigationEvent> = _navigationEvents
}