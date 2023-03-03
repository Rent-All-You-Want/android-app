package com.pablojuice.rayw.feature.preferences.data.local

import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.navigation.NavigationEvents
import com.pablojuice.rayw.R

enum class Preference(
    val title: Int,
    val icon: Int,
    val navigationEvent: NavigationEvent = NavigationEvents.EmptyNavigationEvent
) {
    APP_SETTINGS(
        title = R.string.preference_settings,
        icon = com.pablojuice.core.R.drawable.ic_settings_medium
    ),
    HELP(
        title = R.string.preference_help,
        icon = com.pablojuice.core.R.drawable.ic_support_medium
    ),
    PRIVACY_POLICY(
        title = R.string.preference_privacy_policy,
        icon = com.pablojuice.core.R.drawable.ic_security_medium
    ),
    ABOUT_APP(
        title = R.string.preference_about_app,
        icon = com.pablojuice.core.R.drawable.ic_android_medium
    )
}