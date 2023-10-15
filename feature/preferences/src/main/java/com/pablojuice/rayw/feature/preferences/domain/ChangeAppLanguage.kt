package com.pablojuice.rayw.feature.preferences.domain

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.pablojuice.core.app.settings.language.AppLanguage
import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import javax.inject.Inject

class ChangeAppLanguage @Inject constructor(
    private val userPreferences: UserPreferences
) {

    operator fun invoke(language: AppLanguage) {
        userPreferences.put(UserPreference.APP_LANGUAGE, language.code)
        val appLocale = LocaleListCompat.forLanguageTags(language.code)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}