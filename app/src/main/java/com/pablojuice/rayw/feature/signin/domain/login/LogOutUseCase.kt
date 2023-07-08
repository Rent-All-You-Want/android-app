package com.pablojuice.rayw.feature.signin.domain.login

import com.pablojuice.core.app.manager.AppManager
import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.auth.UserManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val RESTART_DELAY = 1000L

class LogOutUseCase @Inject constructor(
    private val userManager: UserManager,
    private val userPreferences: UserPreferences,
    private val appManager: AppManager
) : () -> Unit {

    override fun invoke() {
        runBlocking {
            userPreferences.clear()
            userPreferences.put(UserPreference.ON_BOARDING_VIEWED, true)
            userManager.clearUser()
            delay(RESTART_DELAY)
            appManager.restartApp()
        }
    }
}