package com.pablojuice.core.data.manager

enum class UserPreference(
    val details: PreferenceDetails<*>
) {
    LOGIN(PreferenceDetails(String::class.java)),
    PASS(PreferenceDetails(String::class.java)),
    ON_BOARDING_VIEWED(PreferenceDetails(Boolean::class.java, false)),
    AUTH_TOKEN(PreferenceDetails(String::class.java)),
    REFRESH_TOKEN(PreferenceDetails(String::class.java));

    //TODO
    val encryptedName: String = name.lowercase().filterNot { it == '_' }.reversed()

    class PreferenceDetails<T>(val type: Class<T>, val default: T? = null)
}

