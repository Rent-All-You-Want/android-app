package com.pablojuice.core.data.manager

import com.pablojuice.core.utils.NumberUtils
import com.pablojuice.core.utils.StringUtils

enum class UserPreference(
    val details: PreferenceDetails<*>
) {
    USER_NAME(PreferenceDetails(String::class.java)),
    USER_AVATAR(PreferenceDetails(String::class.java)),
    USER_WISHLIST(PreferenceDetails(List::class.java, defaultJsonString = StringUtils.EMPTY_LIST)),

    ACCESS_TOKEN(PreferenceDetails(String::class.java)),
    REFRESH_TOKEN(PreferenceDetails(String::class.java)),

    APP_LANGUAGE(PreferenceDetails(String::class.java, StringUtils.EMPTY)),
    APP_THEME(PreferenceDetails(Int::class.java, NumberUtils.UNDEFINED)),

    ON_BOARDING_VIEWED(PreferenceDetails(Boolean::class.java, false));

    //TODO
    val encryptedName: String = name.lowercase().filterNot { it == '_' }.reversed()

    class PreferenceDetails<T>(
        val type: Class<T>,
        val default: T? = null,
        val defaultJsonString: String = StringUtils.EMPTY_JSON
    )
}

