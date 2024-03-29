package com.pablojuice.rayw.feature.signin.data.remote.response

import com.pablojuice.core.data.remote.api.ApiResponse

data class RegisterResponse(
    val accessToken: String,
    val refreshToken: String
) : ApiResponse