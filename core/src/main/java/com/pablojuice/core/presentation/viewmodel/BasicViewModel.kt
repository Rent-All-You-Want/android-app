package com.pablojuice.core.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.pablojuice.core.R
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.navigation.context.alert.ShowAlertDialogEvent
import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.core.utils.logging.Timber
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BasicViewModel : BaseViewModel() {

    private val errorHandler: ErrorHandler = BasicErrorHandler()

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: Flow<NavigationEvent> = _navigationEvents

    protected fun submitNavigationEvent(event: NavigationEvent) =
        launch { _navigationEvents.emit(event) }

    protected fun launchHandlingError(
        handler: ErrorHandler = errorHandler,
        block: suspend CoroutineScope.() -> Any?,
    ) = launch {
        val result = block.invoke(viewModelScope) as? Result<*>
        result?.onFailure(handler)
    }

    fun interface ErrorHandler : (Throwable) -> Unit

    inner class BasicErrorHandler : ErrorHandler {
        override fun invoke(t: Throwable) {
            Timber.e(t)
            submitNavigationEvent(
                ShowAlertDialogEvent(
                    title = R.string.common_error.asLabel(),
                    description = t.message?.asLabel(),
                    positive = R.string.common_ok.asLabel()
                )
            )
        }
    }
}