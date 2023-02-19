package com.pablojuice.rayw.feature.signin.domain.repository

import com.pablojuice.core.domain.repository.Repository
import com.pablojuice.rayw.feature.signin.data.remote.api.SignInApi
import com.pablojuice.rayw.feature.signin.data.remote.request.AuthRequest
import com.pablojuice.rayw.feature.signin.data.remote.request.LoginRequest
import com.pablojuice.rayw.feature.signin.data.remote.request.RegisterRequest
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignInRepository @Inject constructor(
    private val api: SignInApi
) : Repository() {

    suspend fun auth(request: AuthRequest) = launch { api.auth(request) }

    suspend fun register(request: RegisterRequest) = launch { api.register(request) }

    suspend fun login(request: LoginRequest) = launch { api.login(request) }
}