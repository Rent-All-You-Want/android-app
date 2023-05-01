package com.pablojuice.core.data.remote.api.http.config

import com.pablojuice.core.data.remote.api.http.interception.RequestInterceptor

class DebugNetworkConfig(baseApiUrl: String, customInterceptor: RequestInterceptor) : NetworkConfig(
    baseApiUrl = baseApiUrl,
    withCache = false,
    addProfilerInterceptor = true,
    addBodyLoggingInterceptor = false,
    customInterceptor = customInterceptor
)