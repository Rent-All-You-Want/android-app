package com.pablojuice.rayw.feature.signin.data.remote.api

import com.pablojuice.core.data.remote.api.Api
import com.pablojuice.rayw.feature.signin.data.remote.request.AuthRequest
import com.pablojuice.rayw.feature.signin.data.remote.request.LoginRequest
import com.pablojuice.rayw.feature.signin.data.remote.request.RegisterRequest
import com.pablojuice.rayw.feature.signin.data.remote.response.AuthResponse
import com.pablojuice.rayw.feature.signin.data.remote.response.LoginResponse
import com.pablojuice.rayw.feature.signin.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi : Api {

    @POST("auth/token")
    suspend fun auth(@Body request: AuthRequest): Result<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Result<LoginResponse>
}