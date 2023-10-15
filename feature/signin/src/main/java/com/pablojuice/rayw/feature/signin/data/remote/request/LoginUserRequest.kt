package com.pablojuice.rayw.feature.signin.data.remote.request

import com.pablojuice.core.data.remote.api.ApiRequest

data class LoginUserRequest(val email: String, val password: String) : ApiRequest