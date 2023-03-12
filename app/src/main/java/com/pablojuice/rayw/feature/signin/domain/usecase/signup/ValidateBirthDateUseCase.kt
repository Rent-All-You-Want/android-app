package com.pablojuice.rayw.feature.signin.domain.usecase.signup

import com.pablojuice.core.presentation.text.label.asLabel
import java.util.*
import javax.inject.Inject

class ValidateBirthDateUseCase @Inject constructor() {

    operator fun invoke(dateLong: Long): ValidationResult<Date> {
        return Date(dateLong).run {
            ValidationResult(
                result = this,
                error = if (before(Date())) null else "Date is invalid".asLabel()
            )
        }
    }
}