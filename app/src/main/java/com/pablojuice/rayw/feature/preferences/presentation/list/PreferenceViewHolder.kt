package com.pablojuice.rayw.feature.preferences.presentation.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.label.setLabel
import com.pablojuice.core.presentation.view.layoutInflater
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.rayw.databinding.*

class ProfileViewHolder(parent: ViewGroup) :
    ViewHolder<PreferenceProfileItem, ItemPreferencesProfileBinding>(
        ItemPreferencesProfileBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: PreferenceProfileItem) {
        super.bind(item)
        binding.userIcon.setImageResource(item.userIcon)
        binding.userNameLabel.setLabel(item.userName)
    }
}

class PreferenceLogInViewHolder(parent: ViewGroup) :
    ViewHolder<PreferenceLogInItem, ItemPreferencesLogInBinding>(
        ItemPreferencesLogInBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: PreferenceLogInItem) {
        super.bind(item)
        binding.logInButton.setClickListener(item.onClick)
    }
}

class PreferenceLogOutViewHolder(parent: ViewGroup) :
    ViewHolder<PreferenceLogOutItem, ItemPreferencesLogOutBinding>(
        ItemPreferencesLogOutBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: PreferenceLogOutItem) {
        super.bind(item)
        binding.logOutButton.setClickListener(item.onClick)
    }
}

class PreferenceSectionTitleViewHolder(parent: ViewGroup) :
    ViewHolder<PreferenceTitleItem, ItemPreferencesSectionTitleBinding>(
        ItemPreferencesSectionTitleBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: PreferenceTitleItem) {
        super.bind(item)
        binding.titleLabel.setLabel(item.title)
    }
}

class PreferenceSectionViewHolder(parent: ViewGroup) :
    ViewHolder<PreferenceItem, ItemPreferencesSectionBinding>(
        ItemPreferencesSectionBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: PreferenceItem) {
        super.bind(item)
        binding.sectionIcon.setImageResource(item.icon)
        binding.sectionNameLabel.setLabel(item.title)
        binding.sectionContainer.setClickListener(item.onClick)
    }
}