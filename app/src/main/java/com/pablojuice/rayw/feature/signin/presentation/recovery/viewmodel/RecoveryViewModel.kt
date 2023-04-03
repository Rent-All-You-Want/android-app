package com.pablojuice.rayw.feature.signin.presentation.recovery.viewmodel

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.signin.data.repository.SignInRepository
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.BackToLoginScreen
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToRecoverySuccessScreen
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToSecondRecoveryScreen
import com.pablojuice.rayw.feature.signin.presentation.recovery.navigation.ToThirdRecoveryScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecoveryViewModel @Inject constructor(
    private val repository: SignInRepository
) : BasicViewModel() {

    fun proceedToSecondStep() {
        submitNavigationEvent(ToSecondRecoveryScreen())
    }

    fun proceedToThirdStep() {
        submitNavigationEvent(ToThirdRecoveryScreen())
    }

    fun proceedToSuccessStep() {
        submitNavigationEvent(ToRecoverySuccessScreen())
    }

    fun backToLoginScreen() {
        submitNavigationEvent(BackToLoginScreen())
    }
}