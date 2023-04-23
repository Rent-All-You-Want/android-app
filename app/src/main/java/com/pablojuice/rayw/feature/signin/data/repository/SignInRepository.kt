package com.pablojuice.rayw.feature.signin.data.repository

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.auth.UserManager
import com.pablojuice.core.data.repository.Repository
import com.pablojuice.rayw.feature.signin.data.remote.api.SignInApi
import com.pablojuice.rayw.feature.signin.data.remote.request.*
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignInRepository @Inject constructor(
    private val api: SignInApi,
    private val userPreferences: UserPreferences,
    private val userManager: UserManager
) : Repository() {

    suspend fun auth(request: AuthRequest) = launch {
        api.auth(request).also { result ->
            result.onSuccess { response ->
                userPreferences.put(UserPreference.REFRESH_TOKEN, response.token.refreshToken)
                userPreferences.put(UserPreference.ACCESS_TOKEN, response.token.accessToken)
                userPreferences.put(
                    UserPreference.USER_NAME,
                    "${response.user.firstName} ${response.user.secondName}"
                )
                response.user.avatarImage?.run {
                    userPreferences.put(
                        UserPreference.USER_AVATAR,
                        name
                    )
                }
            }
        }
    }

    suspend fun register(request: RegisterUserRequest) = launch {
        api.register(request).onSuccess { response ->
            userPreferences.put(UserPreference.REFRESH_TOKEN, response.refreshToken)
            userPreferences.put(UserPreference.ACCESS_TOKEN, response.accessToken)
            launch { userManager.updateToken() }
        }
    }

    suspend fun login(request: LoginUserRequest) = launch {
        api.login(request).onSuccess { response ->
            userPreferences.put(UserPreference.REFRESH_TOKEN, response.refreshToken)
            userPreferences.put(UserPreference.ACCESS_TOKEN, response.accessToken)
            launch { userManager.updateToken() }
        }
    }

    suspend fun requestPasswordReset(request: RequestPasswordResetRequest) =
        launch { api.requestPasswordReset(request) }

    suspend fun confirmPasswordReset(request: ConfirmPasswordResetRequest) =
        launch { api.confirmPasswordReset(request) }
}