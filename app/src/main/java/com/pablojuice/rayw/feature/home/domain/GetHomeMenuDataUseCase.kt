package com.pablojuice.rayw.feature.home.domain

import com.pablojuice.core.data.remote.auth.UserManager
import com.pablojuice.rayw.feature.home.data.HomeMenuData
import javax.inject.Inject

class GetHomeMenuDataUseCase @Inject constructor(
    private val userManager: UserManager
) {

    operator fun invoke(itemId: Int): HomeMenuData {
        val userIsLoggedIn = userManager.isUserLoggedIn()
        return HomeMenuData.values()
            .firstOrNull { it.id == itemId }
//            ?.takeIf { it.requireLogin == userIsLoggedIn || userIsLoggedIn }
            ?: HomeMenuData.LOGIN_REDIRECT
    }
}