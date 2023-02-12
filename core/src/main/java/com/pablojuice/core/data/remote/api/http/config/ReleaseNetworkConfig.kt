package com.pablojuice.core.data.remote.api.http.config

class ReleaseNetworkConfig(baseApiUrl: String) : NetworkConfig(
    baseApiUrl = baseApiUrl,
    withCache = true,
    addProfilerInterceptor = false,
    addBodyLoggingInterceptor = false,
    customInterceptor = TODO()
)