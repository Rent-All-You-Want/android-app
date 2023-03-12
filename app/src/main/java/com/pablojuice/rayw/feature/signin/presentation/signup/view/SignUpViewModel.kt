package com.pablojuice.rayw.feature.signin.presentation.signup.view

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.home.presentation.navigation.BackToHomeScreen
import com.pablojuice.rayw.feature.signin.domain.usecase.signup.*
import com.pablojuice.rayw.feature.signin.presentation.signup.navigation.ToSecondSignUpScreen
import com.pablojuice.rayw.feature.signin.presentation.signup.navigation.ToSuccessSignUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateAcceptRules: ValidateAcceptRulesUseCase,
    private val validateName: ValidateNameUseCase,
    private val validateBirthDate: ValidateBirthDateUseCase
) : BasicViewModel() {

    private val _state = MutableStateFlow(UserSignUpState())
    val state: Flow<UserSignUpState> = _state

    fun proceedToSecondStep() {
        _state.value.run {
            setEmail(email)
            setPassword(password)
            setAcceptRules(acceptedRules)
        }
        if (_state.value.isFirstStepDataValid()) {
            submitNavigationEvent(ToSecondSignUpScreen())
        }
    }

    fun setEmail(email: String) {
        val validationResult = validateEmail(email)
        _state.value = _state.value.copy(
            email = validationResult.result,
            emailError = validationResult.error
        )
    }

    fun setPassword(password: String) {
        val validationResult = validatePassword(password)
        _state.value = _state.value.copy(
            password = validationResult.result,
            passwordError = validationResult.error
        )
    }

    fun setAcceptRules(rulesAccepted: Boolean) {
        val validationResult = validateAcceptRules(rulesAccepted)
        _state.value = _state.value.copy(
            acceptedRules = validationResult.result,
            acceptedRulesError = validationResult.error
        )
    }

    fun proceedToSuccessStep() {
        if (_state.value.isFirstStepDataValid()) {
            submitNavigationEvent(ToSuccessSignUpScreen())
        }
    }

    fun backToHomeScreen() = submitNavigationEvent(BackToHomeScreen())
}