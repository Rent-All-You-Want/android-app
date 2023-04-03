package com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel

import com.pablojuice.core.presentation.view.label.Label
import com.pablojuice.core.utils.StringUtils

data class UserRecoveryState(
    val email: String = StringUtils.EMPTY,
    val emailError: Label? = null,

    val code: String = StringUtils.EMPTY,
    val codeError: Label? = null,

    val password: String = StringUtils.EMPTY,
    val passwordError: Label? = null,
) {
    fun isEmailValid() = emailError == null

    fun isCodeValid() = codeError == null

    fun isPasswordValid() = passwordError == null
}