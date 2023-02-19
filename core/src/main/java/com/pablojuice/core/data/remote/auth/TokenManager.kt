package com.pablojuice.core.data.remote.auth

private const val TOKEN_VALID_TIME = 30 * 60 * 1000

class TokenManager {

    private var _token: Token? = null

    val token: String?
        get() {
            if (!_token.isValid()) {
                _token = onTokenExpire()
            }
            return _token?.token
        }

    val tokenRequestInterceptor = TokenRequestInterceptor { token }

    var onTokenExpire: OnTokenExpire? = null
        set(value) {
            field = value
            token
        }

    fun isTokenValid() = _token?.isValid()

    data class Token(
        val token: String,
        val expireTime: Long
    )

    private fun Token?.isValid() = this != null && expireTime > System.currentTimeMillis()


    fun interface OnTokenExpire {
        operator fun invoke(): String?
    }

    private operator fun OnTokenExpire?.invoke() =
        this?.invoke()?.let { token ->
            Token(
                token = token,
                expireTime = System.currentTimeMillis() + TOKEN_VALID_TIME
            )
        }
}