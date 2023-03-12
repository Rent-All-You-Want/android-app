package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {

    operator fun invoke(password: String): ValidationResult<String> {
        return password.trim().run {
            ValidationResult(
                result = this,
                error = if (isNotEmpty() && length >= 8) null else "Password is invalid".asLabel()
            )
        }
    }
}