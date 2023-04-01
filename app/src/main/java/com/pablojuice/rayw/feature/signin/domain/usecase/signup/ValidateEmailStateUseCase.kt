package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.feature.signin.data.state.UserSignUpState
import javax.inject.Inject

class ValidateEmailStateUseCase @Inject constructor() {

    operator fun invoke(
        email: String,
        state: UserSignUpState,
        softError: Boolean
    ): UserSignUpState {
        return email.trim().let { validatedEmail ->
            val error =
                if (validatedEmail.contains("@")) null else if (softError) "" else "Email is invalid"
            state.copy(
                email = validatedEmail,
                emailError = error?.asLabel()
            )
        }
    }
}