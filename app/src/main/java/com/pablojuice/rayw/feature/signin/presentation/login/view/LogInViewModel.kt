package com.pablojuice.rayw.feature.signin.presentation.login.view

import com.pablojuice.core.presentation.viewmodel.BasicViewModel
import com.pablojuice.rayw.feature.home.presentation.navigation.BackToHomeScreen
import com.pablojuice.rayw.feature.signin.presentation.login.navigation.ToLogInSuccessScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : BasicViewModel() {

    fun logIn() {
        submitNavigationEvent(ToLogInSuccessScreen())
    }

    fun backToHome() {
        submitNavigationEvent(BackToHomeScreen())
    }
}