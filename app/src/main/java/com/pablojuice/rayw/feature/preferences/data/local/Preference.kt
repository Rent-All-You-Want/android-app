package com.pablojuice.rayw.feature.preferences.data.local

import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.navigation.NavigationEvents
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.home.presentation.navigation.ToUnimplementedScreen
import com.pablojuice.rayw.feature.preferences.presentation.navigation.ToAboutAppScreen
import com.pablojuice.rayw.feature.preferences.presentation.navigation.ToAppSettingsScreen
import com.pablojuice.rayw.feature.preferences.presentation.navigation.ToDevOptionsScreen
import com.pablojuice.core.presentation.R as CoreR

enum class Preference(
    val title: Int? = null,
    val icon: Int? = null,
    val sectionTitle: Int? = null,
    val shouldBeVisibleForUser: (userIsLoggedIn: Boolean) -> Boolean = { true },
    val navigationEvent: NavigationEvent = NavigationEvents.EmptyNavigationEvent
) {

    LOGO,

    EMPTY_PROFILE_CARD(shouldBeVisibleForUser = { !it }),
    PROFILE_CARD(shouldBeVisibleForUser = { it }),
    LOG_IN_PROPOSAL(shouldBeVisibleForUser = { !it }),

    DEV_OPTIONS(
        title = R.string.dev_options,
        icon = CoreR.drawable.ic_terminal_medium,
        navigationEvent = ToDevOptionsScreen(),
        shouldBeVisibleForUser = { false }
    ),

    MY_BORROWING(
        sectionTitle = R.string.preference_section_rents,
        title = R.string.preference_rent_borrowing,
        icon = CoreR.drawable.ic_android_medium,
        navigationEvent = ToUnimplementedScreen(),
        shouldBeVisibleForUser = { it }
    ),
    MY_LENDING(
        title = R.string.preference_rent_lending,
        icon = CoreR.drawable.ic_android_medium,
        navigationEvent = ToUnimplementedScreen(),
        shouldBeVisibleForUser = { it }
    ),
    PENDING_RENTS(
        title = R.string.preference_pending_rents,
        icon = CoreR.drawable.ic_android_medium,
        navigationEvent = ToUnimplementedScreen(),
        shouldBeVisibleForUser = { it }
    ),
    RENT_REQUESTS(
        title = R.string.preference_rent_requests,
        icon = CoreR.drawable.ic_android_medium,
        navigationEvent = ToUnimplementedScreen(),
        shouldBeVisibleForUser = { it }
    ),
    STATISTICS(
        title = R.string.preference_statistics,
        icon = CoreR.drawable.ic_insights_medium,
        navigationEvent = ToUnimplementedScreen(),
        shouldBeVisibleForUser = { it }
    ),

    APP_SETTINGS(
        sectionTitle = R.string.preference_section_settings,
        title = R.string.preference_app_settings,
        navigationEvent = ToAppSettingsScreen(),
        icon = CoreR.drawable.ic_settings_medium
    ),
    PROFILE_SETTINGS(
        title = R.string.preference_profile_settings,
        icon = CoreR.drawable.ic_manage_accounts_medium,
        navigationEvent = ToUnimplementedScreen(),
        shouldBeVisibleForUser = { it }
    ),
    HELP(
        title = R.string.preference_help,
        navigationEvent = ToUnimplementedScreen(),
        icon = CoreR.drawable.ic_support_agent_medium,
    ),

    PRIVACY_POLICY(
        sectionTitle = R.string.preference_section_legal,
        title = R.string.preference_privacy_policy,
        navigationEvent = ToUnimplementedScreen(),
        icon = CoreR.drawable.ic_security_medium
    ),
    TERMS_OF_USE(
        title = R.string.preference_terms_of_use,
        navigationEvent = ToUnimplementedScreen(),
        icon = CoreR.drawable.ic_gavel_medium
    ),

    ABOUT_RAYW(
        sectionTitle = R.string.preference_section_about,
        title = R.string.preference_about_rayw,
        navigationEvent = ToUnimplementedScreen(),
        icon = CoreR.drawable.ic_logo_rayw
    ),
    ABOUT_APP(
        title = R.string.preference_about_app,
        navigationEvent = ToAboutAppScreen(),
        icon = CoreR.drawable.ic_info_medium
    ),

    LOG_OUT(shouldBeVisibleForUser = { it })
}