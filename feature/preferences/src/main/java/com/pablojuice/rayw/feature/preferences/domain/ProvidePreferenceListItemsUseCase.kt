package com.pablojuice.rayw.feature.preferences.domain

import com.pablojuice.core.app.config.AppConfig
import com.pablojuice.core.data.remote.auth.UserManager
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.rayw.feature.preferences.R
import com.pablojuice.rayw.feature.preferences.data.local.Preference
import com.pablojuice.rayw.feature.preferences.presentation.list.list.PreferenceEmptyProfileItem
import com.pablojuice.rayw.feature.preferences.presentation.list.list.PreferenceItem
import com.pablojuice.rayw.feature.preferences.presentation.list.list.PreferenceLogInItem
import com.pablojuice.rayw.feature.preferences.presentation.list.list.PreferenceLogOutItem
import com.pablojuice.rayw.feature.preferences.presentation.list.list.PreferenceProfileItem
import com.pablojuice.rayw.feature.preferences.presentation.list.list.PreferenceTitleItem
import javax.inject.Inject

class ProvidePreferenceListItemsUseCase @Inject constructor(
    private val appConfig: AppConfig,
    private val userManager: UserManager,
) {
    operator fun invoke(): List<ListItem> {
        return mutableListOf<ListItem>().apply {
            val userIsLoggedIn = userManager.isUserLoggedIn()

            Preference.values().filter { it.shouldBeVisibleForUser(userIsLoggedIn) }
                .forEach { preference ->
                    preference.sectionTitle?.let { add(PreferenceTitleItem(it.asLabel())) }

                    if (preference.title != null && preference.icon != null) {
                        add(PreferenceItem(preference))
                    } else when (preference) {
                        Preference.EMPTY_PROFILE_CARD -> add(PreferenceEmptyProfileItem())
                        Preference.LOG_IN_PROPOSAL -> add(PreferenceLogInItem())
                        Preference.PROFILE_CARD -> addProfileSection()
                        Preference.LOG_OUT -> add(PreferenceLogOutItem())
                        else -> Unit
                    }
                }
            if (appConfig.debuggingEnabled) addDevOptionsItem()
        }
    }

    private fun MutableList<ListItem>.addProfileSection() {
        userManager.managedUser?.name?.let { userName ->
            add(
                PreferenceProfileItem(
                    "$userName".asLabel(),
                    R.drawable.ic_mock_avatar
                )
            )
        }
    }

    private fun MutableList<ListItem>.addDevOptionsItem() =
        add(indexOfFirst { it is PreferenceTitleItem }, PreferenceItem(Preference.DEV_OPTIONS))
}
