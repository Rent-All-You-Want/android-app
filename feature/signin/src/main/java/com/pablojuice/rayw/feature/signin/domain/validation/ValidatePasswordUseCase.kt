package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.text.Label
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(
    private val validator: SignInFieldValidator
) {

    operator fun invoke(password: String, softError: Boolean): Label? {
        return validator.validatePassword(password)?.asLabel(softError)
    }
}