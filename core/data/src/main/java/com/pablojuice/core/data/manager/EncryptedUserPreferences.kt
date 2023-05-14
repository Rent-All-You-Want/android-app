package com.pablojuice.core.data.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.pablojuice.core.data.converter.StringJsonConverter
import com.pablojuice.core.utils.StringUtils
import java.lang.reflect.Type

private const val PREFERENCES_KEY = "RAYW_KEY"
private const val DEFAULT_STRING = StringUtils.EMPTY
private const val DEFAULT_INT = 0
private const val DEFAULT_BOOLEAN = false
private const val DEFAULT_OBJECT = StringUtils.EMPTY_JSON

class EncryptedUserPreferences constructor(
    context: Context,
    private val jsonConverter: StringJsonConverter
) : UserPreferences {

    private val preferences: SharedPreferences = context.createPreferences(PREFERENCES_KEY)

    override fun <T> put(key: UserPreference, value: T) {
        preferences.edit().run {
            val keyName = key.encryptedName
            when (value) {
                is Int -> putInt(keyName, value)
                is Boolean -> putBoolean(keyName, value)
                is String -> putString(keyName, value)
                else -> putJsonObject(keyName, value, key.details.type)
            }
            apply()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: UserPreference): T? = preferences.run {
        val keyName = key.encryptedName
        val default = key.details.default
        return when (key.details.type) {
            String::class.java -> getString(keyName, default as? String ?: DEFAULT_STRING) as T
            Boolean::class.java -> getBoolean(keyName, default as? Boolean ?: DEFAULT_BOOLEAN) as T
            Int::class.java -> getInt(keyName, default as? Int ?: DEFAULT_INT) as T
            else -> getJsonObject(
                keyName, key.details.type,
                DEFAULT_OBJECT
            )
        }
    }

    override fun remove(key: UserPreference) = preferences.edit().remove(key.encryptedName).apply()

    override fun clear() = preferences.edit().clear().apply()

    private fun <T> SharedPreferences.Editor.putJsonObject(key: String, value: T, type: Type) =
        putString(key, jsonConverter.toJson(value, type))

    private fun <T> SharedPreferences.getJsonObject(key: String, type: Type, default: String): T? =
        jsonConverter.fromJson<T>(getString(key, default)!!, type)

    private fun Context.createPreferences(preferencesName: String): SharedPreferences =
        EncryptedSharedPreferences.create(
            this,
            preferencesName,
            MasterKey.Builder(this).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
}