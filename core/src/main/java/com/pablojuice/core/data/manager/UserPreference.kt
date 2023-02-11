package com.pablojuice.core.data.manager

enum class UserPreference(
    val details: Details<*>
) {
    LOGIN(Details(String::class.java)),
    PASS(Details(String::class.java));

    //TODO
    val encryptedName: String = name.lowercase().filterNot { it == '_' }.reversed()

    class Details<T>(val type: Class<T>, val default: T? = null)
}

