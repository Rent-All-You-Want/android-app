package com.pablojuice.rayw.feature.preferences.presentation.list

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.pablojuice.core.presentation.base.list.Adapter
import com.pablojuice.core.presentation.base.list.ListItem
import com.pablojuice.core.presentation.base.list.ViewHolder
import com.pablojuice.rayw.R
import com.pablojuice.rayw.databinding.ItemPreferencesLoginBinding
import com.pablojuice.rayw.databinding.ItemPreferencesProfileBinding
import com.pablojuice.rayw.databinding.ItemPreferencesSectionBinding
import com.pablojuice.rayw.databinding.ItemPreferencesSectionTitleBinding

class PreferencesAdapter(items: List<ListItem> = emptyList()) : Adapter<ListItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListItem, out ViewBinding> {
        return when (viewType) {
            R.layout.item_preferences_profile -> ProfileViewHolder(
                ItemPreferencesProfileBinding.inflate(
                    parent.layoutInflater,
                    parent,
                    false
                )
            )
            R.layout.item_preferences_login -> PreferenceLogInViewHolder(
                ItemPreferencesLoginBinding.inflate(
                    parent.layoutInflater,
                    parent,
                    false
                )
            )
            R.layout.item_preferences_section_title -> PreferenceSectionTitleViewHolder(
                ItemPreferencesSectionTitleBinding.inflate(
                    parent.layoutInflater,
                    parent,
                    false
                )
            )
            else -> PreferenceSectionViewHolder(
                ItemPreferencesSectionBinding.inflate(
                    parent.layoutInflater,
                    parent,
                    false
                )
            )
        }
    }
}