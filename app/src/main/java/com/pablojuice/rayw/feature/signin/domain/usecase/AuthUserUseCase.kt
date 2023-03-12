package com.pablojuice.rayw.feature.signin.domain.usecase

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.rayw.feature.signin.data.remote.request.AuthRequest
import com.pablojuice.rayw.feature.signin.domain.repository.SignInRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class AuthUserUseCase @Inject constructor(
    private val repository: SignInRepository,
    private val userPreferences: UserPreferences
) {

    suspend operator fun invoke(tokenToRefresh: String): String? {
        var currentToken: String? = null
        repository.auth(AuthRequest(tokenToRefresh)).getOrNull()?.run {
            userPreferences.put(UserPreference.REFRESH_TOKEN, token.refreshToken)
            userPreferences.put(UserPreference.ACCESS_TOKEN, token.accessToken)
            userPreferences.put(UserPreference.USER_NAME, "${user.firstName} ${user.secondName}")
            userPreferences.put(UserPreference.USER_AVATAR, user.avatarImage.name)
            currentToken = token.accessToken
        }
        return currentToken
    }
}