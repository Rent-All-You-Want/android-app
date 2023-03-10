package com.pablojuice.rayw.feature.preferences.domain

import com.pablojuice.core.R
import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.data.remote.auth.UserManager
import com.pablojuice.core.presentation.base.list.ListItem
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.rayw.feature.home.presentation.navigation.ToDevOptionsScreen
import com.pablojuice.rayw.feature.home.presentation.navigation.ToLoginScreen
import com.pablojuice.rayw.feature.preferences.data.local.Preference
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferenceItem
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferenceLogInItem
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferenceProfileItem
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferenceTitleItem
import javax.inject.Inject

class ProvidePreferenceListItemsUseCase @Inject constructor(
    private val appConfig: AppConfig,
    private val userManager: UserManager
) {
    operator fun invoke(navigationHandler: (NavigationEvent) -> Unit): List<ListItem> {
        return mutableListOf<ListItem>().apply {
            Preference.values().filter { it.shouldBeVisibleForUser(userManager.isUserLoggedIn()) }
                .forEach { preference ->
                    preference.sectionTitle?.let { addSectionTitle(it) }
                    if (preference.title != null && preference.icon != null) {
                        addSectionItem(preference, navigationHandler)
                    } else addCustomItem(preference, navigationHandler)
                }
            addDevOptionsItem(navigationHandler)
        }
    }

    private fun MutableList<ListItem>.addCustomItem(
        preference: Preference,
        navigationHandler: (NavigationEvent) -> Unit
    ) {
        when (preference) {
            Preference.EMPTY_PROFILE_CARD -> addProfileSection("User is not logged in :(")
            Preference.PROFILE_CARD -> addProfileSection("User is logged in :)")
            Preference.LOG_IN_PROPOSAL -> addLoginProposalSection(navigationHandler)
            Preference.LOG_OUT -> addLoginProposalSection(navigationHandler)
            else -> Unit
        }
    }

    private fun MutableList<ListItem>.addProfileSection(username: String) {
        add(PreferenceProfileItem(username.asLabel(), R.drawable.ic_account_circle_medium))
    }

    private fun MutableList<ListItem>.addLoginProposalSection(navigationHandler: (NavigationEvent) -> Unit) {
        add(PreferenceLogInItem { navigationHandler(ToLoginScreen()) })
    }

    private fun MutableList<ListItem>.addSectionTitle(title: Int) {
        add(PreferenceTitleItem(title.asLabel()))
    }

    private fun MutableList<ListItem>.addSectionItem(
        item: Preference,
        navigationHandler: (NavigationEvent) -> Unit,
    ) {
        add(
            PreferenceItem(
                item.title!!.asLabel(),
                item.icon!!,
            ) { navigationHandler(item.navigationEvent) },
        )
    }

    private fun MutableList<ListItem>.addDevOptionsItem(navigationHandler: (NavigationEvent) -> Unit) {
        if (appConfig.debuggingEnabled) {
            add(
                PreferenceItem(
                    "Dev Options".asLabel(),
                    R.drawable.ic_android_medium,
                ) { navigationHandler(ToDevOptionsScreen()) },
            )
        }
    }
}
