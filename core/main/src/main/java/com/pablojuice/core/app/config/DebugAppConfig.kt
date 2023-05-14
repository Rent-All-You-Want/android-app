package com.pablojuice.core.app.config

import com.pablojuice.core.data.remote.api.http.config.NetworkConfig
import com.pablojuice.core.utils.logging.Timber

class DebugAppConfig(
    info: AppInfo,
    networkConfig: NetworkConfig
) : AppConfig(
    info = info,
    debuggingEnabled = true,
    networkConfig = networkConfig,
    splashAnimationDuration = 0,
    loggerType = Timber.DebugTree()
)