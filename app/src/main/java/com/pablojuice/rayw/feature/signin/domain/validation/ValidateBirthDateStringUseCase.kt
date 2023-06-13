package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.text.Label
import javax.inject.Inject

class ValidateBirthDateStringUseCase @Inject constructor(
    private val validator: SignInFieldValidator
) {

    operator fun invoke(dateString: String): Label? {
        return validator.validateBirthDateString(dateString)?.asLabel()
    }
}