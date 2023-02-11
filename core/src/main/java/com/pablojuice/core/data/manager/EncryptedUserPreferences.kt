package com.pablojuice.core.data.manager

import android.content.Context
import android.content.SharedPreferences
import com.pablojuice.core.data.converter.StringJsonConverter
import com.pablojuice.core.utils.StringUtils
import java.lang.reflect.Type
import javax.inject.Inject

private const val KEY = "RAYW_KEY"

class EncryptedUserPreferences @Inject constructor(
    context: Context,
    private val jsonConverter: StringJsonConverter
) : UserPreferences {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(KEY, Context.MODE_PRIVATE)

    override fun <T> put(key: UserPreference, value: T) = preferences.edit().run {
        val keyName = key.encryptedName
        when (value) {
            is Int -> putInt(keyName, value)
            is Boolean -> putBoolean(keyName, value)
            is String -> putString(keyName, value)
            else -> putJsonObject(keyName, value, key.details.type)
        }
        apply()
    }

    override fun <T> get(key: UserPreference): T? = preferences.run {
        val keyName = key.encryptedName
        return when (val default = key.details.default) {
            is String -> getString(keyName, default) as T
            is Boolean -> getBoolean(keyName, default) as T
            is Int -> getInt(keyName, default) as T
            else -> getJsonObject(keyName, key.details.type)
        }
    }

    override fun clear() = preferences.edit().clear().apply()

    private fun <T> SharedPreferences.Editor.putJsonObject(key: String, value: T, type: Type) =
        putString(key, jsonConverter.toJson(value, type))

    private fun <T> SharedPreferences.getJsonObject(key: String, type: Type): T? =
        jsonConverter.fromJson<T>(getString(key, StringUtils.EMPTY_JSON)!!, type)
}