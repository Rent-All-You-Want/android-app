package com.pablojuice.rayw.feature.signin.presentation.signup.viewmodel

import com.pablojuice.core.presentation.view.label.Label

data class UserSignUpState(
    val email: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val emailError: Label? = null,

    val password: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val passwordError: Label? = null,

    val name: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val nameError: Label? = null,

    val birthDate: String = com.pablojuice.core.utils.StringUtils.EMPTY,
    val birthDateError: Label? = null,

    val acceptedRules: Boolean = false,
    val acceptedRulesError: Label? = null,
) {
    fun isFirstStepDataValid() =
        emailError == null && passwordError == null && acceptedRulesError == null

    fun isSecondStepDataValid() =
        nameError == null && birthDateError == null
}