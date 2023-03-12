package com.pablojuice.core.data.manager

enum class UserPreference(
    val details: PreferenceDetails<*>
) {
    USER_NAME(PreferenceDetails(String::class.java)),
    USER_AVATAR(PreferenceDetails(String::class.java)),

    ACCESS_TOKEN(PreferenceDetails(String::class.java)),
    REFRESH_TOKEN(PreferenceDetails(String::class.java)),

    ON_BOARDING_VIEWED(PreferenceDetails(Boolean::class.java, false));

    //TODO
    val encryptedName: String = name.lowercase().filterNot { it == '_' }.reversed()

    class PreferenceDetails<T>(val type: Class<T>, val default: T? = null)
}

