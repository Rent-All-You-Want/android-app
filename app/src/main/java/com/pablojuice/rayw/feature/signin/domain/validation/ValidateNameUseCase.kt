package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.text.Label
import javax.inject.Inject

class ValidateNameUseCase @Inject constructor(
    private val validator: SignInFieldValidator
) {

    operator fun invoke(name: String, softError: Boolean): Label? {
        return validator.validateName(name)?.asLabel(softError)
    }
}