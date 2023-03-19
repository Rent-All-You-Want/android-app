package com.pablojuice.rayw.feature.signin.domain.usecase

import com.pablojuice.rayw.feature.signin.data.remote.request.AuthRequest
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class AuthUserUseCase @Inject constructor(
    private val repository: SignInRepository
) {

    suspend operator fun invoke(tokenToRefresh: String) =
        repository.auth(AuthRequest(tokenToRefresh)).getOrNull()?.token?.accessToken
}