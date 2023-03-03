package com.pablojuice.rayw.feature.preferences.presentation.list

import com.pablojuice.core.presentation.base.list.ListItem
import com.pablojuice.core.presentation.base.list.ViewHolder
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.rayw.databinding.ItemPreferencesLoginBinding
import com.pablojuice.rayw.databinding.ItemPreferencesProfileBinding
import com.pablojuice.rayw.databinding.ItemPreferencesSectionBinding
import com.pablojuice.rayw.databinding.ItemPreferencesSectionTitleBinding

class PreferenceSectionViewHolder(binding: ItemPreferencesSectionBinding) :
    ViewHolder<ListItem, ItemPreferencesSectionBinding>(binding) {

    override fun bind(item: ListItem) {
        if (item is PreferenceItem) {
            binding.sectionIcon.setImageResource(item.icon)
            binding.sectionNameLabel.setLabel(item.title)
            binding.openSectionButton.setOnClickListener { item.onClick() }
        }
    }
}

class ProfileViewHolder(binding: ItemPreferencesProfileBinding) :
    ViewHolder<ListItem, ItemPreferencesProfileBinding>(binding) {

    override fun bind(item: ListItem) {
        if (item is PreferenceProfileItem) {
            binding.userIcon.setImageResource(item.userIcon)
            binding.userNameLabel.setLabel(item.userName)
        }
    }
}

class PreferenceLogInViewHolder(binding: ItemPreferencesLoginBinding) :
    ViewHolder<ListItem, ItemPreferencesLoginBinding>(binding) {

    override fun bind(item: ListItem) {
        if (item is PreferenceLogInItem) {
            binding.logInButton.setOnClickListener { item.onClick() }
        }
    }
}

class PreferenceSectionTitleViewHolder(binding: ItemPreferencesSectionTitleBinding) :
    ViewHolder<ListItem, ItemPreferencesSectionTitleBinding>(binding) {

    override fun bind(item: ListItem) {
        if (item is PreferenceTitleItem) {
            binding.titleLabel.setLabel(item.title)
        }
    }
}