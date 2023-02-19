package com.pablojuice.core.app.config

import com.pablojuice.core.data.remote.api.http.config.NetworkConfig
import com.pablojuice.core.utils.logging.Timber

class DebugAppConfig(
    networkConfig: NetworkConfig
) : AppConfig(
    debuggingEnabled = true,
    networkConfig = networkConfig,
    splashAnimationDuration = 500,
    loggerType = Timber.DebugTree()
)