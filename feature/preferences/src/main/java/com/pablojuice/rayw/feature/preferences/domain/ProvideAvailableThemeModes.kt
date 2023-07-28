package com.pablojuice.rayw.feature.preferences.domain

import com.pablojuice.core.app.settings.theme.AppTheme
import com.pablojuice.core.presentation.view.text.Label
import javax.inject.Inject

class ProvideAvailableThemeModes @Inject constructor() {

    operator fun invoke(): List<Label> =
        AppTheme.values().map { language -> language.displayName }
}