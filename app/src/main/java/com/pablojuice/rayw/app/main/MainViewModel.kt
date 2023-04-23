package com.pablojuice.rayw.app.main

import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.auth.UserManager
import com.pablojuice.core.presentation.viewmodel.BaseViewModel
import com.pablojuice.core.utils.NumberUtils.UNDEFINED
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.signin.domain.login.AuthUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appConfig: AppConfig,
    private val userPreferences: UserPreferences,
    private val authUserUseCase: AuthUserUseCase,
    private val userManager: UserManager
) : BaseViewModel() {

    private val _isFetchingData = MutableStateFlow(true)
    val isFetchingData: StateFlow<Boolean> = _isFetchingData

    private val _navigationGraphId = MutableStateFlow(UNDEFINED)
    val navigationGraphId: StateFlow<Int> = _navigationGraphId


    fun fetchData() {
        if (navigationGraphId.value != UNDEFINED) return
        launch {
            listOf(
                setupSplash(),
                setupApp(),
                setupNavigation()
            ).joinAll()
            _isFetchingData.value = false
        }
    }

    private fun setupApp() = launch {
        Timber.plant(appConfig.loggerType)
        userManager.onTokenExpire =
            UserManager.OnTokenExpire { runBlocking { authUserUseCase(it) } }
    }

    private fun setupSplash() = launch { delay(appConfig.splashAnimationDuration) }

    private fun setupNavigation() = launch {
        val onBoardingWasViewed: Boolean =
            userPreferences.getUnsafe(UserPreference.ON_BOARDING_VIEWED)
        _navigationGraphId.value =
            if (onBoardingWasViewed) R.navigation.main_graph else R.navigation.onboarding_graph
    }

}