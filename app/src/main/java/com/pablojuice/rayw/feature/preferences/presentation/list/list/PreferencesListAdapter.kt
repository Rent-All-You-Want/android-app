package com.pablojuice.rayw.feature.preferences.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.rayw.R
import com.pablojuice.rayw.feature.preferences.data.local.Preference

class PreferencesListAdapter(
    private val listener: Listener
) : ListAdapter(addDividerDecoration = true) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = when (viewType) {
        R.layout.item_preferences_empty_profile -> EmptyProfileViewHolder(parent)
        R.layout.item_preferences_profile -> ProfileViewHolder(parent)
        R.layout.item_preferences_log_in ->
            PreferenceLogInViewHolder(parent, listener::onLogInClick)
        R.layout.item_preferences_log_out ->
            PreferenceLogOutViewHolder(parent, listener::onLogOutClick)
        R.layout.item_preferences_section_title -> PreferenceSectionTitleViewHolder(parent)
        R.layout.item_preferences_section ->
            PreferenceSectionViewHolder(parent, listener::onSectionClick)
        else -> TODO()
    }

    interface Listener {
        fun onLogInClick()
        fun onLogOutClick()
        fun onSectionClick(section: Preference)
    }
}