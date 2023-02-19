package com.pablojuice.rayw.feature.signin.data.remote.request

import com.pablojuice.core.data.remote.api.ApiRequest

data class AuthRequest(val refreshToken: String) : ApiRequest()