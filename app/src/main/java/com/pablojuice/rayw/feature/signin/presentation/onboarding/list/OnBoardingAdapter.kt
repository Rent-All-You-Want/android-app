package com.pablojuice.rayw.feature.signin.presentation.onboarding.list

import android.view.ViewGroup
import com.pablojuice.core.presentation.base.list.Adapter
import com.pablojuice.core.presentation.base.list.ViewHolder
import com.pablojuice.core.presentation.text.label.setLabel
import com.pablojuice.rayw.databinding.ItemOnboardingBinding
import com.pablojuice.rayw.feature.signin.data.local.OnBoardingItem

class OnBoardingAdapter(items: List<OnBoardingItem>) : Adapter<OnBoardingItem>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OnBoardingViewHolder(
        ItemOnboardingBinding.inflate(parent.layoutInflater, parent, false)
    )
}

class OnBoardingViewHolder(binding: ItemOnboardingBinding) :
    ViewHolder<OnBoardingItem, ItemOnboardingBinding>(binding) {

    override fun bind(item: OnBoardingItem) {
        binding.icon.setImageResource(item.icon)
        binding.title.setLabel(item.title)
        binding.description.setLabel(item.description)
    }
}