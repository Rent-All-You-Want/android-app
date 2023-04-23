package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.label.Label
import javax.inject.Inject

class ValidateRecoveryCodeUseCase @Inject constructor(
    private val validator: SignInFieldValidator
) {
    operator fun invoke(enteredCode: String, validCode: String, softError: Boolean): Label? {
        return validator.validateCode(enteredCode, validCode)?.asLabel(softError)
    }
}