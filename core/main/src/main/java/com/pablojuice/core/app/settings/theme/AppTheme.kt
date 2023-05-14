package com.pablojuice.core.app.settings.theme

import androidx.appcompat.app.AppCompatDelegate
import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.label.Label
import com.pablojuice.core.presentation.view.label.asLabel

enum class AppTheme(val code: Int, val displayName: Label) {

    DEFAULT(
        code = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
        displayName = R.string.common_theme_default.asLabel()
    ),
    LIGHT(
        code = AppCompatDelegate.MODE_NIGHT_NO,
        displayName = R.string.common_theme_light.asLabel()
    ),
    DARK(
        code = AppCompatDelegate.MODE_NIGHT_YES,
        displayName = R.string.common_theme_dark.asLabel()
    );

    companion object {
        fun fromCode(code: Int): AppTheme =
            AppTheme.values().firstOrNull { theme -> theme.code == code } ?: DEFAULT
    }
}