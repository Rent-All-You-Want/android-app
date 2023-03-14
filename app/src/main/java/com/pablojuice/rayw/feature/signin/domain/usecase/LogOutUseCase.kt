package com.pablojuice.rayw.feature.signin.domain.usecase

import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.auth.UserManager
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val userManager: UserManager,
    private val userPreferences: UserPreferences
) {

    operator fun invoke() {
        userPreferences.clear()
        userManager.clearUser()
    }
}