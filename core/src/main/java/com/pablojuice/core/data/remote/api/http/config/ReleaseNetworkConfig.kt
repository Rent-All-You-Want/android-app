package com.pablojuice.core.data.remote.api.http.config

import com.pablojuice.core.data.remote.api.http.interception.RequestInterceptor

class ReleaseNetworkConfig(baseApiUrl: String, customInterceptor: RequestInterceptor) :
    NetworkConfig(
        baseApiUrl = baseApiUrl,
        withCache = true,
        addProfilerInterceptor = false,
        addBodyLoggingInterceptor = false,
        customInterceptor = customInterceptor
    )