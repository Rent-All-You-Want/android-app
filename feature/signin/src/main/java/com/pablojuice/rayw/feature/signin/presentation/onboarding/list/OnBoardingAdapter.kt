package com.pablojuice.rayw.feature.signin.presentation.onboarding.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.view.layout.layoutInflater
import com.pablojuice.core.presentation.view.list.ListAdapter
import com.pablojuice.core.presentation.view.list.ViewHolder
import com.pablojuice.core.presentation.view.text.setLabel
import com.pablojuice.rayw.feature.signin.databinding.ItemOnboardingBinding
import com.pablojuice.rayw.feature.signin.domain.onboarding.OnBoardingListItem

class OnBoardingAdapter(items: List<OnBoardingListItem>) : ListAdapter(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OnBoardingViewHolder(parent)
}

class OnBoardingViewHolder(parent: ViewGroup) :
    ViewHolder<OnBoardingListItem, ItemOnboardingBinding>(
        ItemOnboardingBinding.inflate(parent.layoutInflater, parent, false)
    ) {

    override fun bind(item: OnBoardingListItem) {
        binding.icon.setImageResource(item.icon)
        binding.title.setLabel(item.title)
        binding.description.setLabel(item.description)
    }
}