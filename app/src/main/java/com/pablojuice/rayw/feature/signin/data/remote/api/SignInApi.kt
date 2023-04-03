package com.pablojuice.rayw.feature.signin.data.remote.api

import com.pablojuice.core.data.remote.api.Api
import com.pablojuice.rayw.feature.signin.data.remote.request.*
import com.pablojuice.rayw.feature.signin.data.remote.response.*
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi : Api {

    @POST("auth/token")
    suspend fun auth(@Body request: AuthRequest): Result<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Result<LoginResponse>

    @POST("otp/start-reset-password-session")
    suspend fun requestPasswordReset(@Body request: RequestPasswordResetRequest): Result<RequestPasswordResetResponse>

    @POST("otp/reset-password")
    suspend fun confirmPasswordReset(@Body request: ConfirmPasswordResetRequest): Result<ConfirmPasswordResetResponse>
}