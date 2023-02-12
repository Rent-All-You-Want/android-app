package com.pablojuice.core.app.config

import com.pablojuice.core.data.remote.api.http.config.NetworkConfig

abstract class AppConfig(
    val debuggingEnabled: Boolean,
    val networkConfig: NetworkConfig,
    val splashAnimationDuration: Long
)