package com.pablojuice.core.data.remote.auth

import com.pablojuice.core.data.remote.api.http.interception.RequestInterceptor
import okhttp3.Interceptor
import okhttp3.Response

class TokenRequestInterceptor(
    private var tokenProvider: () -> String?,
) : RequestInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        tokenProvider()?.let { token ->
            if (token.isNotEmpty()) {
                request = request.newBuilder()
                    .addHeader("Authorization", "Bearer $token").build()
            }
        }
        return chain.proceed(request)
    }
}