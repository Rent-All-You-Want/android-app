package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import javax.inject.Inject

class ValidatePasswordRepeatUseCase @Inject constructor() {

    operator fun invoke(password: String, passwordRepeat: String): ValidationResult<String> {
        return passwordRepeat.trim().run {
            ValidationResult(
                result = this,
                error = if (isNotEmpty() && passwordRepeat == password) null else "Password Repeat is invalid".asLabel()
            )
        }
    }
}