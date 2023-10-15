package com.pablojuice.rayw.feature.preferences.domain

import androidx.appcompat.app.AppCompatDelegate
import com.pablojuice.core.app.settings.theme.AppTheme
import com.pablojuice.core.data.manager.UserPreference
import com.pablojuice.core.data.manager.UserPreferences
import javax.inject.Inject

class ChangeAppTheme @Inject constructor(
    private val userPreferences: UserPreferences
) {

    operator fun invoke(theme: AppTheme) {
        userPreferences.put(UserPreference.APP_THEME, theme.code)
        AppCompatDelegate.setDefaultNightMode(theme.code)
    }
}