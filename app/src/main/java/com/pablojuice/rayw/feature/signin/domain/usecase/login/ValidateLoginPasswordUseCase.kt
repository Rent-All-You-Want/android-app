package com.pablojuice.rayw.feature.signin.domain.usecase.login

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.feature.signin.data.state.UserLogInState
import javax.inject.Inject

class ValidateLoginPasswordUseCase @Inject constructor() {

    operator fun invoke(
        password: String,
        state: UserLogInState,
        softError: Boolean
    ): UserLogInState {
        return password.trim().let { validated ->
            val error =
                if (validated.isNotEmpty()) null else if (softError) "" else "Email is invalid"
            state.copy(
                password = validated,
                passwordError = error?.asLabel()
            )
        }
    }
}