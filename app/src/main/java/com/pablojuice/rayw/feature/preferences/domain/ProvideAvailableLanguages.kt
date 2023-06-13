package com.pablojuice.rayw.feature.preferences.domain

import com.pablojuice.core.app.settings.language.AppLanguage
import com.pablojuice.core.presentation.view.text.Label
import javax.inject.Inject

class ProvideAvailableLanguages @Inject constructor() {

    operator fun invoke(): List<Label> =
        AppLanguage.values().map { language -> language.displayName }
}