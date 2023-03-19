package com.pablojuice.rayw.feature.signin.presentation.signup.view

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.feature.home.presentation.navigation.BackToHomeScreen
import com.pablojuice.rayw.feature.signin.data.remote.request.RegisterRequest
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import com.pablojuice.rayw.feature.signin.data.state.UserSignUpState
import com.pablojuice.rayw.feature.signin.domain.usecase.signup.*
import com.pablojuice.rayw.feature.signin.presentation.signup.navigation.ToSecondSignUpScreen
import com.pablojuice.rayw.feature.signin.presentation.signup.navigation.ToSuccessSignUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmail: ValidateEmailStateUseCase,
    private val validatePassword: ValidatePasswordStateUseCase,
    private val validateAcceptRules: ValidateAcceptRulesStateUseCase,
    private val validateName: ValidateNameStateUseCase,
    private val validateBirthDate: ValidateBirthDateStateUseCase,
    private val validateDateString: ValidateBirthDateStringStateUseCase,
    private val repo: SignInRepository
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
        _state.value = validateEmail(email, _state.value, softError)
    }

    fun setPassword(password: String, softError: Boolean = true) {
        _state.value = validatePassword(password, _state.value, softError)
    }

    fun setAcceptRules(rulesAccepted: Boolean) {
        _state.value = validateAcceptRules(rulesAccepted, _state.value)
    }

    fun setName(name: String, softError: Boolean = true) {
        _state.value = validateName(name, _state.value, softError)
    }

    fun setBirthDate(dateMillis: Long? = null, dateString: String? = null) {
        dateMillis?.let { _state.value = validateBirthDate(dateMillis, _state.value) }
        dateString?.let { _state.value = validateDateString(dateString, _state.value) }
    }

    fun proceedToSuccessStep() {
        _state.value.run {
            setName(name, false)
            setBirthDate(dateString = birthDate)
        }
        _state.value.run {
            launch {
                if (isSecondStepDataValid()) {
                    repo.register(toRegisterRequest()).onSuccess {
                        submitNavigationEvent(ToSuccessSignUpScreen())
                    }.onFailure {
                        Timber.e(it)
                    }
                }
            }
        }
    }

    private fun UserSignUpState.toRegisterRequest() = RegisterRequest(
        firstName = name,
        secondName = name,
        birthDate = birthDate,
        email = email,
        password = password
    )

    fun backToHomeScreen() = submitNavigationEvent(BackToHomeScreen())
}