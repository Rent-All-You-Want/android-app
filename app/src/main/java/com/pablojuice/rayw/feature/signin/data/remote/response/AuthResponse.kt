package com.pablojuice.rayw.feature.signin.data.remote.response

import com.pablojuice.core.data.remote.api.ApiResponse

data class AuthResponse(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String
) : ApiResponse()