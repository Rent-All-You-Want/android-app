package com.pablojuice.rayw.feature.preferences.presentation.list.list

import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ListItemDivider
import com.pablojuice.core.presentation.view.text.Label
import com.pablojuice.rayw.feature.preferences.R
import com.pablojuice.rayw.feature.preferences.data.local.Preference

class PreferenceEmptyProfileItem : ListItem(R.layout.item_preferences_empty_profile)

class PreferenceProfileItem(val userName: Label, val userIcon: Int) :
    ListItem(R.layout.item_preferences_profile)

class PreferenceLogInItem : ListItem(R.layout.item_preferences_log_in)

class PreferenceLogOutItem : ListItem(R.layout.item_preferences_log_out)

class PreferenceTitleItem(val title: Label) : ListItem(R.layout.item_preferences_section_title)

class PreferenceItem(val preference: Preference) :
    ListItem(R.layout.item_preferences_section, ListItemDivider.Type.LARGE)