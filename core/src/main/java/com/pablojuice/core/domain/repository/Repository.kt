package com.pablojuice.core.domain.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class Repository(private val context: CoroutineContext = Dispatchers.IO) {

    suspend fun <T> launch(block: suspend CoroutineScope.() -> T) = withContext(context, block)
}