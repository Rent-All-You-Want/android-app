package com.pablojuice.rayw.feature.signin.presentation.login.view

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.feature.home.presentation.navigation.BackToHomeScreen
import com.pablojuice.rayw.feature.signin.data.remote.request.LoginRequest
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import com.pablojuice.rayw.feature.signin.data.state.UserLogInState
import com.pablojuice.rayw.feature.signin.domain.usecase.login.ValidateLoginEmailUseCase
import com.pablojuice.rayw.feature.signin.domain.usecase.login.ValidateLoginPasswordUseCase
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToLogInSuccessScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val validateEmail: ValidateLoginEmailUseCase,
    private val validatePassword: ValidateLoginPasswordUseCase,
    private val repo: SignInRepository
) : BasicViewModel() {

    private val _state = MutableStateFlow(UserLogInState())
    val state: Flow<UserLogInState> = _state

    fun logIn() {
        _state.value.run {
            setEmail(email, false)
            setPassword(password, false)
        }
        _state.value.run {
            launchHandlingError {
                if (isDataValid()) {
                    repo.login(toLogInRequest())
                        .onSuccess { submitNavigationEvent(ToLogInSuccessScreen()) }
                        .onFailure { Timber.e(it) }
                } else Unit
            }
        }
    }

    fun setEmail(email: String, softError: Boolean = true) {
        _state.value = validateEmail(email, _state.value, softError)
    }

    fun setPassword(password: String, softError: Boolean = true) {
        _state.value = validatePassword(password, _state.value, softError)
    }

    fun backToHome() = submitNavigationEvent(BackToHomeScreen())

    private fun UserLogInState.toLogInRequest() = LoginRequest(email, password)
}