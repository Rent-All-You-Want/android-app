package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.label.Label
import java.util.Date
import javax.inject.Inject

class ValidateBirthDateUseCase @Inject constructor(
    private val validator: SignInFieldValidator
) {

    operator fun invoke(date: Date): Label? {
        return validator.validateBirthDate(date)?.asLabel()
    }
}