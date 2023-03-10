package com.pablojuice.core.data.remote.auth

import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import com.pablojuice.core.utils.logging.Timber

private const val TOKEN_VALID_TIME = 15 * 60 * 1000

class UserManager(
    private val userPreferences: UserPreferences
) {

    private var _token: Token? = null

    fun isUserLoggedIn() = _token.isValid()

    val token: String?
        get() {
            if (!_token.isValid()) {
                Timber.i("Token is not valid")
                userPreferences.getUnsafe<String>(UserPreference.REFRESH_TOKEN)
                    .let { tokenToRefresh ->
                        Timber.i("Updating token with refresh token $tokenToRefresh")
                        if (tokenToRefresh.isNotEmpty())
                            onTokenExpire(tokenToRefresh)?.run {
                                _token = this
                                Timber.i("New token received $this")
                            }
                    }
            }
            return _token?.token
        }

    val tokenRequestInterceptor = TokenRequestInterceptor { token }

    var onTokenExpire: OnTokenExpire? = null
        set(value) {
            field = value
            token
        }

    data class Token(
        val token: String,
        val expireTime: Long
    )

    private fun Token?.isValid() = this != null && expireTime > System.currentTimeMillis()

    fun interface OnTokenExpire {
        operator fun invoke(refreshToken: String): String?
    }

    private operator fun OnTokenExpire?.invoke(refreshToken: String) =
        this?.invoke(refreshToken)?.let { token ->
            Token(
                token = token,
                expireTime = System.currentTimeMillis() + TOKEN_VALID_TIME
            )
        }
}