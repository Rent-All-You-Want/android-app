package com.pablojuice.rayw.feature.preferences.presentation.list.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.setClickListener
import com.pablojuice.core.presentation.view.text.asLabel
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.rayw.databinding.ItemPreferencesEmptyProfileBinding
import com.pablojuice.rayw.databinding.ItemPreferencesLogInBinding
import com.pablojuice.rayw.databinding.ItemPreferencesLogOutBinding
import com.pablojuice.rayw.databinding.ItemPreferencesProfileBinding
import com.pablojuice.rayw.databinding.ItemPreferencesSectionBinding
import com.pablojuice.rayw.databinding.ItemPreferencesSectionTitleBinding
import com.pablojuice.rayw.feature.preferences.data.local.Preference

class EmptyProfileViewHolder(parent: ViewGroup) :
    ViewHolder<PreferenceEmptyProfileItem, ItemPreferencesEmptyProfileBinding>(
        ItemPreferencesEmptyProfileBinding.inflate(parent.layoutInflater, parent, false)
    )

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

class PreferenceLogInViewHolder(parent: ViewGroup, private val onClick: () -> Unit) :
    ViewHolder<PreferenceLogInItem, ItemPreferencesLogInBinding>(
        ItemPreferencesLogInBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: PreferenceLogInItem) {
        super.bind(item)
        binding.logInButton.setClickListener(onClick)
    }
}

class PreferenceLogOutViewHolder(parent: ViewGroup, private val onClick: () -> Unit) :
    ViewHolder<PreferenceLogOutItem, ItemPreferencesLogOutBinding>(
        ItemPreferencesLogOutBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: PreferenceLogOutItem) {
        super.bind(item)
        binding.logOutButton.setClickListener(onClick)
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

class PreferenceSectionViewHolder(parent: ViewGroup, private val onClick: (Preference) -> Unit) :
    ViewHolder<PreferenceItem, ItemPreferencesSectionBinding>(
        ItemPreferencesSectionBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: PreferenceItem) {
        super.bind(item)
        item.preference.run {
            icon?.let { binding.sectionIcon.setImageResource(icon) }
            title?.let { binding.sectionNameLabel.setLabel(title.asLabel()) }
            binding.sectionContainer.setOnClickListener { onClick(this) }
        }
    }
}