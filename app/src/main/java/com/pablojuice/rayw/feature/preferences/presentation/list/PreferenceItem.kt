package com.pablojuice.rayw.feature.preferences.presentation.list

import com.pablojuice.core.presentation.text.label.Label
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ListItemDivider
import com.pablojuice.rayw.R

class PreferenceProfileItem(val userName: Label, val userIcon: Int) :
    ListItem(R.layout.item_preferences_profile)

class PreferenceLogInItem(val onClick: () -> Unit) : ListItem(R.layout.item_preferences_log_in)

class PreferenceLogOutItem(val onClick: () -> Unit) : ListItem(R.layout.item_preferences_log_out)

class PreferenceTitleItem(val title: Label) : ListItem(R.layout.item_preferences_section_title)

class PreferenceItem(val title: Label, val icon: Int, val onClick: () -> Unit) :
    ListItem(R.layout.item_preferences_section, ListItemDivider.Type.LARGE)