package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import javax.inject.Inject

class ValidateAcceptRulesUseCase @Inject constructor() {

    operator fun invoke(rulesAccepted: Boolean): ValidationResult<Boolean> {
        return ValidationResult(
            result = rulesAccepted,
            error = if (rulesAccepted) null else "* Required".asLabel()
        )
    }
}