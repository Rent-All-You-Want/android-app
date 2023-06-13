package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.text.Label
import javax.inject.Inject

class ValidateAcceptRulesUseCase @Inject constructor(
    private val validator: SignInFieldValidator
) {

    operator fun invoke(rulesAccepted: Boolean): Label? {
        return validator.validateAcceptedRules(rulesAccepted)?.asLabel()
    }
}