package com.pablojuice.rayw.feature.preferences.data.local

import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.navigation.NavigationEvents
import com.pablojuice.rayw.R

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

    MY_RENTS_IN(
        sectionTitle = R.string.preference_section_rents,
        title = R.string.preference_rent_in,
        icon = com.pablojuice.core.R.drawable.ic_android_medium,
        shouldBeVisibleForUser = { it }
    ),
    MY_RENTS_OUT(
        title = R.string.preference_rent_out,
        icon = com.pablojuice.core.R.drawable.ic_android_medium,
        shouldBeVisibleForUser = { it }
    ),
    PENDING_RENTS(
        title = R.string.preference_pending_rents,
        icon = com.pablojuice.core.R.drawable.ic_android_medium,
        shouldBeVisibleForUser = { it }
    ),
    RENT_REQUESTS(
        title = R.string.preference_rent_requests,
        icon = com.pablojuice.core.R.drawable.ic_android_medium,
        shouldBeVisibleForUser = { it }
    ),
    STATISTICS(
        title = R.string.preference_statistics,
        icon = com.pablojuice.core.R.drawable.ic_insights_medium,
        shouldBeVisibleForUser = { it }
    ),

    APP_SETTINGS(
        sectionTitle = R.string.preference_section_settings,
        title = R.string.preference_app_settings,
        icon = com.pablojuice.core.R.drawable.ic_settings_medium
    ),
    PROFILE_SETTINGS(
        title = R.string.preference_profile_settings,
        icon = com.pablojuice.core.R.drawable.ic_manage_accounts_medium,
        shouldBeVisibleForUser = { it }
    ),
    HELP(
        title = R.string.preference_help,
        icon = com.pablojuice.core.R.drawable.ic_support_agent_medium
    ),

    PRIVACY_POLICY(
        sectionTitle = R.string.preference_section_legal,
        title = R.string.preference_privacy_policy,
        icon = com.pablojuice.core.R.drawable.ic_security_medium
    ),
    TERMS_OF_USE(
        title = R.string.preference_terms_of_use,
        icon = com.pablojuice.core.R.drawable.ic_gavel_medium
    ),

    ABOUT_RAYW(
        sectionTitle = R.string.preference_section_about,
        title = R.string.preference_about_rayw,
        icon = com.pablojuice.core.R.drawable.ic_logo_rayw
    ),
    ABOUT_APP(
        title = R.string.preference_about_app,
        icon = com.pablojuice.core.R.drawable.ic_info_medium
    ),

    LOG_OUT(shouldBeVisibleForUser = { it })
}