package com.pablojuice.rayw.feature.signin.domain.usecase.login

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.feature.signin.presentation.login.viewmodel.UserLogInState
import javax.inject.Inject

class ValidateLoginEmailUseCase @Inject constructor() {

    operator fun invoke(email: String, state: UserLogInState, softError: Boolean): UserLogInState {
        return email.trim().let { validated ->
            val error =
                if (validated.contains("@")) null else if (softError) "" else "Email is invalid"
            state.copy(
                email = validated,
                emailError = error?.asLabel()
            )
        }
    }
}