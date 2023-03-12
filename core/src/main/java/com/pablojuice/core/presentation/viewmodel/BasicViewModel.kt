package com.pablojuice.core.presentation.viewmodel

import com.pablojuice.core.presentation.navigation.NavigationEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BasicViewModel : BaseViewModel() {

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: Flow<NavigationEvent> = _navigationEvents

    protected fun submitNavigationEvent(event: NavigationEvent) =
        launch { _navigationEvents.emit(event) }
}