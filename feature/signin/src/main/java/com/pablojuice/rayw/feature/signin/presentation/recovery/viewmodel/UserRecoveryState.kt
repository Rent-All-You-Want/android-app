package com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel

import com.pablojuice.core.presentation.view.text.Label

data class UserRecoveryState(
    val email: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val emailError: Label? = null,

    val code: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val codeError: Label? = null,

    val password: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val passwordError: Label? = null,

    val passwordConfirmation: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val passwordConfirmationError: Label? = null,
) {
    fun isEmailValid() = emailError == null

    fun isCodeValid() = codeError == null

    fun isPasswordValid() = passwordError == null && passwordConfirmationError == null
}