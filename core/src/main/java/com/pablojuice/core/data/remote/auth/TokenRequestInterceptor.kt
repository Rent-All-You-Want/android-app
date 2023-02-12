package com.pablojuice.core.data.remote.auth

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.api.http.interception.RequestInterceptor
import okhttp3.Interceptor
import okhttp3.Response

class TokenRequestInterceptor(
    userPreferences: UserPreferences
) : RequestInterceptor {
    private var tokenProvider: (() -> String?)? = { userPreferences.get(UserPreference.AUTH_TOKEN) }
    private lateinit var token: String

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        if (token.isEmpty()) {
            tokenProvider?.invoke()?.let { possibleToken ->
                token = possibleToken
                tokenProvider = null
            }
        }
        if (::token.isInitialized)
            request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}