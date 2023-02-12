package com.pablojuice.core.app.config

import com.pablojuice.core.data.remote.api.http.config.NetworkConfig

class DebugAppConfig(
    networkConfig: NetworkConfig
) : AppConfig(
    debuggingEnabled = true,
    networkConfig = networkConfig,
    splashAnimationDuration = 1000
)