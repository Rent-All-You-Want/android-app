package com.pablojuice.core.app.config

import com.pablojuice.core.data.remote.api.http.config.NetworkConfig

class ReleaseAppConfig(
    networkConfig: NetworkConfig
) : AppConfig(
    debuggingEnabled = false,
    networkConfig = networkConfig,
    splashAnimationDuration = 3000
)