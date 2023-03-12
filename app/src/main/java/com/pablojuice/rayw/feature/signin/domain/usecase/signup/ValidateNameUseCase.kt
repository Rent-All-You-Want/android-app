package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import javax.inject.Inject

class ValidateNameUseCase @Inject constructor() {

    operator fun invoke(name: String): ValidationResult<String> {
        return name.trim().run {
            ValidationResult(
                result = this,
                error = if (isNotEmpty() && length >= 5) null else "Name is invalid".asLabel()
            )
        }
    }
}