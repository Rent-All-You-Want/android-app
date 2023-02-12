package com.pablojuice.core.data.remote.api.http.config

class DebugNetworkConfig(baseApiUrl: String) : NetworkConfig(
    baseApiUrl = baseApiUrl,
    withCache = true,
    addProfilerInterceptor = true,
    addBodyLoggingInterceptor = false,
    customInterceptor = null
)