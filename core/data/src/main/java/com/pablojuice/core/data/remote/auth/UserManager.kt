package com.pablojuice.core.data.remote.auth

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.data.remote.NetworkHelper
import com.pablojuice.core.utils.logging.Timber

private const val TOKEN_VALID_TIME = 15 * 60 * 1000

class UserManager(
    private val userPreferences: UserPreferences
) {

    private var isUpdatingUser: Boolean = false

    var managedUser: ManagedUser? = null
        private set

    fun isUserLoggedIn() = managedUser.isTokenValid()

    fun clearUser() {
        managedUser = null
    }

    val token: String?
        get() {
            if (!managedUser.isTokenValid() && !isUpdatingUser) {
                Timber.i("Token is not valid")
                updateToken()
            }
            return managedUser?.token
        }

    val tokenRequestInterceptor = TokenRequestInterceptor { token }

    var onTokenExpire: OnTokenExpire? = null
        set(value) {
            field = value
            if (tokenCanBeUpdated()) updateToken()
        }

    private fun ManagedUser?.isTokenValid() =
        this != null && expireTime > System.currentTimeMillis()

    fun updateToken() {
        isUpdatingUser = true
        userPreferences.getUnsafe<String>(UserPreference.REFRESH_TOKEN)
            .let { tokenToRefresh ->
                if (tokenToRefresh.isNotEmpty()) {
                    Timber.i("Updating token with refresh token $tokenToRefresh")
                    onTokenExpire(tokenToRefresh)?.run {
                        managedUser = this
                        isUpdatingUser = false
                        Timber.i("New token received $this")
                    }
                } else isUpdatingUser = false
            }
    }

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

    private fun tokenCanBeUpdated(): Boolean =
        NetworkHelper.isOnline() || NetworkHelper.isOnline() || NetworkHelper.isOnline()
}