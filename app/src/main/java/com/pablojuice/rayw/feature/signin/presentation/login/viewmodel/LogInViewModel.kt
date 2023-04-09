package com.pablojuice.rayw.feature.signin.presentation.login.viewmodel

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.home.presentation.navigation.BackToHomeScreen
import com.pablojuice.rayw.feature.signin.domain.login.LogInUserUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidateEmailUseCase
import com.pablojuice.rayw.feature.signin.domain.validation.ValidatePasswordUseCase
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToLogInSuccessScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val logInUser: LogInUserUseCase
) : BasicViewModel() {

    private val _state = MutableStateFlow(UserLogInState())
    val state: Flow<UserLogInState> = _state

    fun logIn() {
        _state.value.run {
            setEmail(email, false)
            setPassword(password, false)
        }
        if (_state.value.isDataValid()) launchHandlingError {
            logInUser(_state.value)
                .onSuccess { submitNavigationEvent(ToLogInSuccessScreen()) }
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

    fun backToHome() = submitNavigationEvent(BackToHomeScreen())
}