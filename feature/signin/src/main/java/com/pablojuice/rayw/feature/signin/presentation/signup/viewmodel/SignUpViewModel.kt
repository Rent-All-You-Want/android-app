package com.pablojuice.rayw.feature.signin.presentation.signup.viewmodel

import com.pablojuice.core.app.navigation.BackToHomeScreen
import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.core.utils.toSimpleDateFormat
import com.pablojuice.rayw.feature.signin.domain.signup.RegisterUserUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateAcceptRulesUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateBirthDateStringUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateBirthDateUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateEmailUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateNameUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidatePasswordUseCase
import com.pablojuice.rayw.feature.signin.presentation.signup.navigation.ToSecondSignUpScreen
import com.pablojuice.rayw.feature.signin.presentation.signup.navigation.ToSuccessSignUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateAcceptRules: ValidateAcceptRulesUseCase,
    private val validateName: ValidateNameUseCase,
    private val validateBirthDate: ValidateBirthDateUseCase,
    private val validateDateString: ValidateBirthDateStringUseCase,
    private val registerUser: RegisterUserUseCase
) : BasicViewModel() {

    private val _state = MutableStateFlow(UserSignUpState())
    val state: Flow<UserSignUpState> = _state

    fun proceedToSecondStep() {
        _state.value.run {
            setEmail(email, false)
            setPassword(password, false)
            setAcceptRules(acceptedRules)
        }
        if (_state.value.isFirstStepDataValid()) {
            submitNavigationEvent(ToSecondSignUpScreen())
        }
    }

    fun setEmail(email: String, softError: Boolean = true) {
        _state.value = _state.value.copy(
            email = email,
            emailError = validateEmail(email, softError)
        )
    }

    fun setPassword(password: String, softError: Boolean = true) {
        _state.value = _state.value.copy(
            password = password,
            passwordError = validatePassword(password, softError)
        )
    }

    fun setAcceptRules(rulesAccepted: Boolean) {
        _state.value = _state.value.copy(
            acceptedRules = rulesAccepted,
            acceptedRulesError = validateAcceptRules(rulesAccepted)
        )
    }

    fun setName(name: String, softError: Boolean = true) {
        _state.value = _state.value.copy(
            name = name,
            nameError = validateName(name, softError)
        )
    }

    fun setBirthDate(dateMillis: Long) {
        val birthDate = Date(dateMillis)
        _state.value = _state.value.copy(
            birthDate = birthDate.toSimpleDateFormat(),
            birthDateError = validateBirthDate(birthDate)
        )
    }

    private fun setBirthDayString(dateString: String) {
        _state.value = _state.value.copy(
            birthDate = dateString,
            birthDateError = validateDateString(dateString)
        )
    }

    fun proceedToSuccessStep() {
        _state.value.run {
            setName(name, false)
            setBirthDayString(birthDate)
        }
        if (_state.value.isSecondStepDataValid()) launchHandlingError {
            registerUser(_state.value)
                .onSuccess { submitNavigationEvent(ToSuccessSignUpScreen()) }
        }
    }

    fun backToHomeScreen() = submitNavigationEvent(BackToHomeScreen())
}