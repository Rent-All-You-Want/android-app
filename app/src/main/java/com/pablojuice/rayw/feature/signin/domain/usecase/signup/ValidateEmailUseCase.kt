package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): ValidationResult<String> {
        return email.trim().run {
            ValidationResult(
                result = this,
                error = if (isNotEmpty() && contains("@")) null else "Email is invalid".asLabel()
            )
        }
    }
}