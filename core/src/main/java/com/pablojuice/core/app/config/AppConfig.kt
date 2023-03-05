package com.pablojuice.core.app.config

import com.pablojuice.core.data.remote.api.http.config.NetworkConfig
import com.pablojuice.core.utils.logging.Timber

abstract class AppConfig(
    val debuggingEnabled: Boolean,
    val networkConfig: NetworkConfig,
    val splashAnimationDuration: Long,
    val loggerType: Timber.Tree
)