package com.pablojuice.rayw.feature.preferences.domain

import com.pablojuice.core.R
import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.data.remote.auth.UserManager
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.feature.home.presentation.navigation.ToDevOptionsScreen
import com.pablojuice.rayw.feature.home.presentation.navigation.ToLogOutDialog
import com.pablojuice.rayw.feature.home.presentation.navigation.ToLoginScreen
import com.pablojuice.rayw.feature.preferences.data.local.Preference
import com.pablojuice.rayw.feature.preferences.presentation.list.*
import com.pablojuice.rayw.feature.signin.domain.usecase.LogOutUseCase
import javax.inject.Inject

class ProvidePreferenceListItemsUseCase @Inject constructor(
    private val appConfig: AppConfig,
    private val userManager: UserManager,
    private val logOutUseCase: LogOutUseCase
) {
    operator fun invoke(navigationHandler: (NavigationEvent) -> Unit): List<ListItem> {
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
        navigationHandler: (NavigationEvent) -> Unit
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
        add(PreferenceProfileItem(username.asLabel(), R.drawable.ic_account_circle_filled_medium))

    private fun MutableList<ListItem>.addLogInProposalSection(
        navigationHandler: (NavigationEvent) -> Unit
    ) = add(PreferenceLogInItem { navigationHandler(ToLoginScreen()) })

    private fun MutableList<ListItem>.addLogOutProposalSection(
        navigationHandler: (NavigationEvent) -> Unit
    ) = add(PreferenceLogOutItem { navigationHandler(ToLogOutDialog(logOutUseCase)) })

    private fun MutableList<ListItem>.addSectionTitle(title: Int) =
        add(PreferenceTitleItem(title.asLabel()))

    private fun MutableList<ListItem>.addSectionItem(
        item: Preference,
        navigationHandler: (NavigationEvent) -> Unit,
    ) = add(
        PreferenceItem(
            item.title!!.asLabel(),
            item.icon!!,
        ) { navigationHandler(item.navigationEvent) },
    )

    private fun MutableList<ListItem>.addDevOptionsItem(navigationHandler: (NavigationEvent) -> Unit) =
        add(
            indexOfFirst { it is PreferenceTitleItem },
            PreferenceItem(
                com.pablojuice.rayw.R.string.dev_options.asLabel(),
                R.drawable.ic_terminal_medium,
            ) { navigationHandler(ToDevOptionsScreen()) },
        )
}
