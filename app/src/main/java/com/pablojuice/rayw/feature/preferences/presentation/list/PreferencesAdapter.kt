package com.pablojuice.rayw.feature.preferences.presentation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.list.Adapter
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.rayw.R

class PreferencesAdapter : Adapter<ListItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_preferences_profile -> ProfileViewHolder(parent)
        R.layout.item_preferences_log_in -> PreferenceLogInViewHolder(parent)
        R.layout.item_preferences_log_out -> PreferenceLogOutViewHolder(parent)
        R.layout.item_preferences_section_title -> PreferenceSectionTitleViewHolder(parent)
        else -> PreferenceSectionViewHolder(parent)
    }
}