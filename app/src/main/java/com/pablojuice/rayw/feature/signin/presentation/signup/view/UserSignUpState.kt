package com.pablojuice.rayw.feature.signin.presentation.signup.view

import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.utils.StringUtils

data class UserSignUpState(
    val email: String = StringUtils.EMPTY,
    val emailError: Label? = null,

    val password: String = StringUtils.EMPTY,
    val passwordError: Label? = null,

//    val passwordRepeat: String = StringUtils.EMPTY,
//    val passwordRepeatError: Label? = null,

    val name: String = StringUtils.EMPTY,
    val nameError: Label? = null,

    val birthDate: String = StringUtils.EMPTY,
    val birthDateError: Label? = null,

    val acceptedRules: Boolean = false,
    val acceptedRulesError: Label? = null,
) {
    fun isFirstStepDataValid() =
        emailError == null && passwordError == null && acceptedRulesError == null

    fun isSecondStepDataValid() =
        nameError == null && birthDateError == null
}