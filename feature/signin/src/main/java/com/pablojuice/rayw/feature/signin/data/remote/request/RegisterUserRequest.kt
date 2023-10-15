package com.pablojuice.rayw.feature.signin.data.remote.request

import com.pablojuice.core.data.remote.api.ApiRequest

data class RegisterUserRequest(
    val firstName: String,
    val secondName: String,
    val birthDate: String,
    val email: String,
    val password: String
) : ApiRequest