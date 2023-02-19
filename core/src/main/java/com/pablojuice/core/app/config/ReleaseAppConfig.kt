package com.pablojuice.core.app.config

import com.pablojuice.core.data.remote.api.http.config.NetworkConfig
import com.pablojuice.core.utils.logging.ReleaseTree

class ReleaseAppConfig(
    networkConfig: NetworkConfig
) : AppConfig(
    debuggingEnabled = false,
    networkConfig = networkConfig,
    splashAnimationDuration = 1000,
    loggerType = ReleaseTree()
)