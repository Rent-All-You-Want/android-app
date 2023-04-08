package com.pablojuice.rayw.feature.signin.domain.login

import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.auth.UserManager
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val userManager: UserManager,
    private val userPreferences: UserPreferences
) : () -> Unit {

    override fun invoke() {
        userPreferences.clear()
        userManager.clearUser()
    }
}