package com.pablojuice.core.app.settings.language

import com.pablojuice.core.presentation.R
import com.pablojuice.core.presentation.view.label.Label
import com.pablojuice.core.presentation.view.label.asLabel
import com.pablojuice.core.utils.StringUtils

enum class AppLanguage(val code: String, val displayName: Label) {

    DEFAULT(
        code = StringUtils.EMPTY,
        displayName = R.string.common_language_default.asLabel()
    ),
    ENGLISH(
        code = "en",
        displayName = R.string.common_language_english.asLabel()
    ),
    UKRAINIAN(
        code = "uk",
        displayName = R.string.common_language_ukrainian.asLabel()
    );

    companion object {
        fun fromCode(code: String): AppLanguage =
            values().firstOrNull { language -> language.code == code } ?: DEFAULT
    }
}