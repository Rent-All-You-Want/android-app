package com.pablojuice.rayw.feature.preferences.domain

import com.pablojuice.core.R
import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.data.remote.auth.UserManager
import com.pablojuice.core.presentation.navigation.DirectionalNavigationEvent
import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.home.presentation.navigation.ToDevOptionsScreen
import com.pablojuice.rayw.feature.home.presentation.navigation.ToLogOutDialog
import com.pablojuice.rayw.feature.home.presentation.navigation.ToLoginScreen
import com.pablojuice.rayw.feature.preferences.data.local.Preference
import com.pablojuice.rayw.feature.preferences.presentation.list.*
import javax.inject.Inject

class ProvidePreferenceListItemsUseCase @Inject constructor(
    private val appConfig: AppConfig,
    private val userManager: UserManager
) {
    operator fun invoke(navigationHandler: (DirectionalNavigationEvent) -> Unit): List<ListItem> {
        return mutableListOf<ListItem>().apply {
            val userIsLoggedIn = userManager.isUserLoggedIn()
            Preference.values().filter { it.shouldBeVisibleForUser(userIsLoggedIn) }
                .forEach { preference ->
                    preference.sectionTitle?.let { addSectionTitle(it) }
                    if (preference.title != null && preference.icon != null) {
                        addSectionItem(preference, navigationHandler)
                    } else addCustomItem(preference, navigationHandler)
                }
            if (appConfig.debuggingEnabled) addDevOptionsItem(navigationHandler)
        }
    }

    private fun MutableList<ListItem>.addCustomItem(
        preference: Preference,
        navigationHandler: (DirectionalNavigationEvent) -> Unit
    ) {
        when (preference) {
            Preference.EMPTY_PROFILE_CARD -> addProfileSection("User is not logged in :(")
            Preference.PROFILE_CARD -> addProfileSection("User is logged in :)")
            Preference.LOG_IN_PROPOSAL -> addLogInProposalSection(navigationHandler)
            Preference.LOG_OUT -> addLogOutProposalSection(navigationHandler)
            else -> Unit
        }
    }

    private fun MutableList<ListItem>.addProfileSection(username: String) =
        add(PreferenceProfileItem(username.asLabel(), R.drawable.ic_account_circle_medium))

    private fun MutableList<ListItem>.addLogInProposalSection(
        navigationHandler: (DirectionalNavigationEvent) -> Unit
    ) = add(PreferenceLogInItem { navigationHandler(ToLoginScreen()) })

    private fun MutableList<ListItem>.addLogOutProposalSection(
        navigationHandler: (DirectionalNavigationEvent) -> Unit
    ) = add(PreferenceLogOutItem { navigationHandler(ToLogOutDialog()) })

    private fun MutableList<ListItem>.addSectionTitle(title: Int) =
        add(PreferenceTitleItem(title.asLabel()))

    private fun MutableList<ListItem>.addSectionItem(
        item: Preference,
        navigationHandler: (DirectionalNavigationEvent) -> Unit,
    ) = add(
        PreferenceItem(
            item.title!!.asLabel(),
            item.icon!!,
        ) { navigationHandler(item.navigationEvent) },
    )

    private fun MutableList<ListItem>.addDevOptionsItem(navigationHandler: (DirectionalNavigationEvent) -> Unit) =
        add(
            PreferenceItem(
                "Dev Options".asLabel(),
                R.drawable.ic_android_medium,
            ) { navigationHandler(ToDevOptionsScreen()) },
        )
}
