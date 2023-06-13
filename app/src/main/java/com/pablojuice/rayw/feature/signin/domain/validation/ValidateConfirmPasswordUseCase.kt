package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.text.Label
import javax.inject.Inject

class ValidateConfirmPasswordUseCase @Inject constructor(
    private val validator: SignInFieldValidator
) {

    operator fun invoke(confirmPassword: String, password: String, softError: Boolean): Label? {
        return validator.validateConfirmation(confirmPassword, password)?.asLabel(softError)
    }
}