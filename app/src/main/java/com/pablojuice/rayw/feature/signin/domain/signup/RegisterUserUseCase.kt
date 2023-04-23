package com.pablojuice.rayw.feature.signin.domain.signup

import com.pablojuice.rayw.feature.signin.data.remote.request.RegisterUserRequest
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import com.pablojuice.rayw.feature.signin.presentation.signup.viewmodel.UserSignUpState
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: SignInRepository,
) {

    suspend operator fun invoke(state: UserSignUpState) =
        repository.register(state.asRegisterRequest())

    private fun UserSignUpState.asRegisterRequest(): RegisterUserRequest {
        val nameSplit = name.split(com.pablojuice.core.utils.StringUtils.SPACE)
        val firstName = nameSplit.first()
        val lastName = if (nameSplit.size > 1) nameSplit.subList(1, nameSplit.size)
            .joinToString(separator = com.pablojuice.core.utils.StringUtils.SPACE) else com.pablojuice.core.utils.StringUtils.EMPTY

        return RegisterUserRequest(
            firstName = firstName,
            secondName = lastName,
            birthDate = birthDate,
            email = email,
            password = password
        )
    }
}