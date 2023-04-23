package com.pablojuice.core.data.remote.api.http.config

import com.pablojuice.core.data.remote.api.http.interception.RequestInterceptor

abstract class NetworkConfig(
    val baseApiUrl: String,
    val withCache: Boolean,
    val addProfilerInterceptor: Boolean,
    val addBodyLoggingInterceptor: Boolean,
    val customInterceptor: RequestInterceptor?
)