package com.pablojuice.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    fun <T> launchAsync(
        context: CoroutineContext = Dispatchers.Default,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> = viewModelScope.async(context = context, block = block)
}