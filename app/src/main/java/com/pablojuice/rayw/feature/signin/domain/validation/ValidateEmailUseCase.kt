package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.label.Label
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    private val validator: SignInFieldValidator
) {

    operator fun invoke(email: String, softError: Boolean): Label? {
        return validator.validateEmail(email)?.asLabel(softError)
    }
}