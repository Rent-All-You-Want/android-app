package com.pablojuice.core.data.remote.auth

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.utils.logging.Timber

private const val TOKEN_VALID_TIME = 15 * 60 * 1000

class UserManager(
    private val userPreferences: UserPreferences
) {

    var managedUser: ManagedUser? = null
        private set

    fun isUserLoggedIn() = managedUser.isTokenValid()

    val token: String?
        get() {
            if (!managedUser.isTokenValid()) {
                Timber.i("Token is not valid")
                userPreferences.getUnsafe<String>(UserPreference.REFRESH_TOKEN)
                    .let { tokenToRefresh ->
                        Timber.i("Updating token with refresh token $tokenToRefresh")
                        if (tokenToRefresh.isNotEmpty())
                            onTokenExpire(tokenToRefresh)?.run {
                                managedUser = this
                                Timber.i("New token received $this")
                            }
                    }
            }
            return managedUser?.token
        }

    val tokenRequestInterceptor = TokenRequestInterceptor { token }

    var onTokenExpire: OnTokenExpire? = null
        set(value) {
            field = value
            token
        }

    private fun ManagedUser?.isTokenValid() =
        this != null && expireTime > System.currentTimeMillis()

    fun interface OnTokenExpire {
        operator fun invoke(refreshToken: String): String?
    }

    class ManagedUser(
        val name: String,
        val avatar: String,
        val token: String,
        val expireTime: Long
    )

    private operator fun OnTokenExpire?.invoke(refreshToken: String) =
        this?.invoke(refreshToken)?.let { token ->
            ManagedUser(
                token = token,
                name = userPreferences.getUnsafe(UserPreference.USER_NAME),
                avatar = userPreferences.getUnsafe(UserPreference.USER_AVATAR),
                expireTime = System.currentTimeMillis() + TOKEN_VALID_TIME
            )
        }
}