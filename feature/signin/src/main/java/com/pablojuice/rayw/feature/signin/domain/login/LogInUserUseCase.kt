package com.pablojuice.rayw.feature.signin.domain.login

import com.pablojuice.rayw.feature.signin.data.remote.request.LoginUserRequest
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import com.pablojuice.rayw.feature.signin.presentation.login.viewmodel.UserLogInState
import javax.inject.Inject

class LogInUserUseCase @Inject constructor(
    private val repository: SignInRepository,
) {

    suspend operator fun invoke(state: UserLogInState) =
        repository.login(state.asLogInRequest())

    private fun UserLogInState.asLogInRequest() = LoginUserRequest(email, password)
}