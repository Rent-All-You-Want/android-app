package com.pablojuice.core.data.remote.api.http.error.exception

class NetworkException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)