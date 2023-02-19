package com.pablojuice.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    fun launch(
        context: CoroutineContext = Dispatchers.Default,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context = context, block = block)

    fun launchOnMain(block: suspend CoroutineScope.() -> Unit) =
        launch(context = Dispatchers.Main, block = block)

    fun launchOnIO(block: suspend CoroutineScope.() -> Unit) =
        launch(context = Dispatchers.IO, block = block)
}