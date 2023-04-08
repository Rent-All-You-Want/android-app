package com.pablojuice.rayw.feature.signin.domain.validation

import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.rayw.R
import java.util.*
import javax.inject.Inject

private const val STANDARD_EMAIL_LENGTH = 4
private const val STANDARD_EMAIL_SYMBOL = '@'

private const val STANDARD_PASSWORD_LENGTH = 8

private const val STANDARD_NAME_LENGTH = 3


class SignInFieldValidator @Inject constructor() {

    fun validateEmail(email: String): ValidationError? = email.run {
        if (length < STANDARD_EMAIL_LENGTH) {
            ValidationError(R.string.signin_error_email_too_short)
        } else if (none { it == STANDARD_EMAIL_SYMBOL }) {
            ValidationError(R.string.signin_error_email_is_not_valid)
        } else null
    }

    fun validatePassword(password: String): ValidationError? = password.run {
        if (length < STANDARD_PASSWORD_LENGTH) {
            ValidationError(R.string.signin_error_password_too_short)
        } else if (any { !it.isLetterOrDigit() }) {
            ValidationError(R.string.signin_error_password_contains_unexpected_symbols)
        } else null
    }

    fun validateAcceptedRules(accepted: Boolean?): ValidationError? =
        if (accepted == true) null else ValidationError(R.string.signin_error_rules_should_be_accepted)

    fun validateBirthDate(date: Date): ValidationError? =
        if (date.before(Date())) {
            null
        } else ValidationError(R.string.signin_error_birth_date_is_not_valid)

    fun validateBirthDateString(date: String): ValidationError? =
        if (date.isNotEmpty()) {
            null
        } else ValidationError(R.string.signin_error_birth_date_is_empty)

    fun validateName(name: String): ValidationError? = name.run {
        if (length < STANDARD_NAME_LENGTH) {
            ValidationError(R.string.signin_error_name_too_short)
        } else if (any { !(it.isLetter() || it.isWhitespace()) }) {
            ValidationError(R.string.signin_error_name_contains_unexpected_symbols)
        } else null
    }

    fun validateCode(enteredCode: String, validCode: String): ValidationError? = enteredCode.run {
        if (length != validCode.length) {
            ValidationError(R.string.signin_error_recovery_code_is_too_short)
        } else if (!equals(validCode)) {
            ValidationError(R.string.signin_error_recovery_code_is_not_valid)
        } else null
    }

    class ValidationError(val res: Int) {
        fun asLabel(softError: Boolean = false) = if (softError) null else res.asLabel()
    }
}