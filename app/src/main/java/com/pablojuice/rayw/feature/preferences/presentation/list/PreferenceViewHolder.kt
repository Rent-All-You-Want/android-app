package com.pablojuice.rayw.feature.preferences.presentation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.core.presentation.view.layoutInflater
import com.pablojuice.core.presentation.view.list.ListItem
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.rayw.databinding.*

class ProfileViewHolder(parent: ViewGroup) :
    ViewHolder<ListItem, ItemPreferencesProfileBinding>(
        ItemPreferencesProfileBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: ListItem) {
        if (item is PreferenceProfileItem) {
            binding.userIcon.setImageResource(item.userIcon)
            binding.userNameLabel.setLabel(item.userName)
        }
    }
}

class PreferenceLogInViewHolder(parent: ViewGroup) :
    ViewHolder<ListItem, ItemPreferencesLogInBinding>(
        ItemPreferencesLogInBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: ListItem) {
        if (item is PreferenceLogInItem) {
            binding.logInButton.setClickListener(item.onClick)
        }
    }
}

class PreferenceLogOutViewHolder(parent: ViewGroup) :
    ViewHolder<ListItem, ItemPreferencesLogOutBinding>(
        ItemPreferencesLogOutBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: ListItem) {
        if (item is PreferenceLogOutItem) {
            binding.logOutButton.setClickListener(item.onClick)
        }
    }
}

class PreferenceSectionTitleViewHolder(parent: ViewGroup) :
    ViewHolder<ListItem, ItemPreferencesSectionTitleBinding>(
        ItemPreferencesSectionTitleBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: ListItem) {
        if (item is PreferenceTitleItem) {
            binding.titleLabel.setLabel(item.title)
        }
    }
}

class PreferenceSectionViewHolder(parent: ViewGroup) :
    ViewHolder<ListItem, ItemPreferencesSectionBinding>(
        ItemPreferencesSectionBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: ListItem) {
        if (item is PreferenceItem) {
            binding.sectionIcon.setImageResource(item.icon)
            binding.sectionNameLabel.setLabel(item.title)
            binding.sectionContainer.setClickListener(item.onClick)
        }
    }
}