package com.pablojuice.rayw.feature.signin.domain.signup

import com.pablojuice.rayw.feature.signin.data.remote.request.RegisterRequest
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import com.pablojuice.rayw.feature.signin.presentation.signup.viewmodel.UserSignUpState
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: SignInRepository,
) {

    suspend operator fun invoke(state: UserSignUpState) =
        repository.register(state.asRegisterRequest())

    private fun UserSignUpState.asRegisterRequest() = RegisterRequest(
        firstName = name,
        secondName = name,
        birthDate = birthDate,
        email = email,
        password = password
    )
}