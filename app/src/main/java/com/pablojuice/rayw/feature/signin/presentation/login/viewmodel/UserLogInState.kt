package com.pablojuice.rayw.feature.signin.presentation.login.viewmodel

import com.pablojuice.core.presentation.view.label.Label

data class UserLogInState(
    val email: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val emailError: Label? = null,

    val password: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val passwordError: Label? = null,
) {
    fun isDataValid() = emailError == null && passwordError == null
}