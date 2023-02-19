package com.pablojuice.rayw.app.main

import androidx.lifecycle.viewModelScope
import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.data.remote.auth.TokenManager
import com.pablojuice.core.presentation.base.BaseViewModel
import com.pablojuice.core.utils.logging.Timber
import com.pablojuice.rayw.feature.signin.domain.usecase.RefreshTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appConfig: AppConfig,
    private val tokenManager: TokenManager,
    private val useCase: RefreshTokenUseCase
) : BaseViewModel() {

    private val _isFetchingData = MutableStateFlow(true)
    val isFetchingData: StateFlow<Boolean> = _isFetchingData

    fun fetchData() {
        viewModelScope.run {
            launch {
                listOf(
                    setupApp(),
                    setupSplash()
                ).joinAll()
                _isFetchingData.value = false
            }
        }
    }

    private fun CoroutineScope.setupApp() = launch {
        Timber.plant(appConfig.loggerType)
        tokenManager.onTokenExpire = TokenManager.OnTokenExpire { runBlocking { useCase() } }
    }

    private fun CoroutineScope.setupSplash() = launch { delay(appConfig.splashAnimationDuration) }

}