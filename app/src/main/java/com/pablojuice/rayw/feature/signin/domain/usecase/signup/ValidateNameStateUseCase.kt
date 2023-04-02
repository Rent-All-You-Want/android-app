package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.feature.signin.presentation.signup.viewmodel.UserSignUpState
import javax.inject.Inject

class ValidateNameStateUseCase @Inject constructor() {

    operator fun invoke(
        name: String, state: UserSignUpState,
        softError: Boolean
    ): UserSignUpState {
        return name.trim().let { validatedName ->
            val error =
                if (validatedName.length >= 5) null else if (softError) "" else "Name is invalid"
            state.copy(
                name = validatedName,
                nameError = error?.asLabel()
            )
        }
    }
}