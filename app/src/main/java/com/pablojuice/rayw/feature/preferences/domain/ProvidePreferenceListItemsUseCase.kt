package com.pablojuice.rayw.feature.preferences.domain

import com.pablojuice.core.R
import com.pablojuice.core.presentation.base.list.ListItem
import com.pablojuice.core.presentation.navigation.NavigationEvent
import com.pablojuice.core.presentation.text.label.asLabel
import com.pablojuice.rayw.feature.home.presentation.navigation.ToLoginScreen
import com.pablojuice.rayw.feature.preferences.data.local.Preference
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferenceItem
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferenceLogInItem
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferenceProfileItem
import com.pablojuice.rayw.feature.preferences.presentation.list.PreferenceTitleItem
import javax.inject.Inject

class ProvidePreferenceListItemsUseCase @Inject constructor(

) {
    operator fun invoke(navigationHandler: (NavigationEvent) -> Unit): List<ListItem> {
        return mutableListOf<ListItem>().apply {
            addProfileSection("Pablo")
            addLoginSection(navigationHandler)
            repeat(10) {
                addSectionTitle("Section Header $it")
                Preference.values().forEach { addSectionItem(it, navigationHandler) }
            }
        }
    }

    private fun MutableList<ListItem>.addProfileSection(username: String) {
        add(PreferenceProfileItem(username.asLabel(), R.drawable.ic_account_circle_medium))
    }

    private fun MutableList<ListItem>.addLoginSection(navigationHandler: (NavigationEvent) -> Unit) {
        add(PreferenceLogInItem { navigationHandler(ToLoginScreen()) })
    }

    private fun MutableList<ListItem>.addSectionTitle(title: String) {
        add(PreferenceTitleItem(title.asLabel()))
    }

    private fun MutableList<ListItem>.addSectionItem(
        item: Preference,
        navigationHandler: (NavigationEvent) -> Unit
    ) {
        add(
            PreferenceItem(
                item.title.asLabel(),
                item.icon
            ) { navigationHandler(item.navigationEvent) }
        )
    }
}