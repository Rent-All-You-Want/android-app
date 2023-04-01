package com.pablojuice.rayw.feature.signin.data.state

import com.pablojuice.core.presentation.view.label.Label
import com.pablojuice.core.utils.StringUtils

data class UserLogInState(
    val email: String = StringUtils.EMPTY,
    val emailError: Label? = null,

    val password: String = StringUtils.EMPTY,
    val passwordError: Label? = null,
) {
    fun isDataValid() = emailError == null && passwordError == null
}