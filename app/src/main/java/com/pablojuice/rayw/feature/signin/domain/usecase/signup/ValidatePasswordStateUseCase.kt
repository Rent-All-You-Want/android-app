package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.rayw.feature.signin.data.state.UserSignUpState
import javax.inject.Inject

class ValidatePasswordStateUseCase @Inject constructor() {

    operator fun invoke(
        password: String,
        state: UserSignUpState,
        softError: Boolean
    ): UserSignUpState {
        return password.trim().let { validatedPassword ->
            val error =
                if (validatedPassword.length >= 8) null else if (softError) "" else "Password is invalid"
            state.copy(
                password = validatedPassword,
                passwordError = error?.asLabel()
            )
        }
    }
}