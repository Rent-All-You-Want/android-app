package com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.signin.domain.recovery.ConfirmPasswordResetUseCase
import com.pablojuice.rayw.feature.signin.domain.recovery.RequestPasswordResetUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateConfirmPasswordUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateEmailUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidatePasswordUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateRecoveryCodeUseCase
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.BackToLoginScreen
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToRecoverySuccessScreen
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToSecondRecoveryScreen
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToThirdRecoveryScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RecoveryViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validateCode: ValidateRecoveryCodeUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateConfirmation: ValidateConfirmPasswordUseCase,
    private val requestPasswordReset: RequestPasswordResetUseCase,
    private val confirmPasswordReset: ConfirmPasswordResetUseCase
) : BasicViewModel() {

    private val _state = MutableStateFlow(UserRecoveryState())
    val state: Flow<UserRecoveryState> = _state

    private val _recoveryCode = MutableStateFlow("12345")

    fun setEmail(email: String, softError: Boolean = true) {
        _state.value = _state.value.copy(
            email = email,
            emailError = validateEmail(email, softError)
        )
    }

    fun setRecoveryCode(code: String, softError: Boolean = true) {
        _state.value = _state.value.copy(
            code = code,
            codeError = validateCode(code, _recoveryCode.value, softError)
        )
    }

    fun setPassword(password: String, softError: Boolean = true) {
        _state.value = _state.value.copy(
            password = password,
            passwordError = validatePassword(password, softError)
        )
    }

    fun setConfirmation(confirmation: String, softError: Boolean = true) {
        _state.value.run {
            _state.value = copy(
                passwordConfirmation = confirmation,
                passwordConfirmationError = validateConfirmation(confirmation, password, softError)
            )
        }
    }

    fun proceedToSecondStep() {
        setEmail(_state.value.email, false)
        if (_state.value.isEmailValid()) launchHandlingError {
            requestPasswordReset(_state.value)
                .onSuccess { response ->
                    _recoveryCode.value = response.code
                    submitNavigationEvent(ToSecondRecoveryScreen())
                }
        }
    }

    fun proceedToThirdStep() {
        setRecoveryCode(_state.value.code, false)
        if (_state.value.isCodeValid()) submitNavigationEvent(ToThirdRecoveryScreen())
    }

    fun proceedToSuccessStep() {
        _state.value.run {
            setPassword(password, false)
            setConfirmation(passwordConfirmation, false)
        }
        if (_state.value.isPasswordValid()) launchHandlingError {
            confirmPasswordReset(_state.value)
                .onSuccess { submitNavigationEvent(ToRecoverySuccessScreen()) }
        }
    }

    fun backToLoginScreen() {
        submitNavigationEvent(BackToLoginScreen())
    }
}